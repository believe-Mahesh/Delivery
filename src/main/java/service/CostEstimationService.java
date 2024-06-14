package service;

import dto.OfferDetails;
import dto.PackageDetails;
import exception.InvalidCouponIdException;
import exception.InvalidOfferDetailsFileException;
import offerValidation.OfferCoupon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostEstimationService {
    private static CostEstimationService costEstimationService = null;
    private static Map<String, OfferDetails> offerDetailsMap = new HashMap<>();
    public void calculateCost(double baseDeliveryCost, List<PackageDetails> packageDetailsList) {
        for (PackageDetails packageDetail : packageDetailsList) {
            Double discount = 0.0;
            Double totalCost = getTotalCost(baseDeliveryCost,
                    packageDetail.getWeight(), packageDetail.getDistance());
            packageDetail.setTotalCost(new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_UP)
                    .doubleValue());
            try {
                discount = getDiscountCost(totalCost, packageDetail);
                discount = new BigDecimal(discount).setScale(2, RoundingMode.HALF_UP).doubleValue();
            } catch (InvalidCouponIdException ex) {
                System.out.println(ex.getMessage());
                discount = 0.0;
            }
            packageDetail.setDiscount(discount);
            Double netCost = totalCost - discount;
            netCost = new BigDecimal(netCost).setScale(2, RoundingMode.HALF_UP).doubleValue();
            packageDetail.setNetCost(netCost);
        }
    }

    private static Double getTotalCost(Double baseCost, Double weight, Double distance) {
        return ((weight * 10) + (distance * 5) + baseCost);
    }

    private static Double getDiscountCost(Double totalCost, PackageDetails packageDetails) {
        Double discount = 0.0;
        OfferDetails offerDetails = offerDetailsMap.get(packageDetails.getCouponId());
        if(offerDetails != null) {
            OfferCoupon offerCoupon = new OfferCoupon(offerDetails);
            if (offerCoupon != null) {
                if (offerCoupon.isOfferApplicable(packageDetails.getDistance(),
                        packageDetails.getWeight())) {
                    discount = totalCost * offerCoupon.getDiscount();
                } else {
                    System.out.println("Offer Not applicable for the given package details \n" + packageDetails.toString());
                }
            } else {
                throw new InvalidCouponIdException(packageDetails.getCouponId() + " is not a valid coupon id");
            }
        } else {
            System.err.println(packageDetails.getCouponId() + " is not present in the offer-coupons.csv");
        }
        return discount;
    }

    private CostEstimationService() {
        try {
            populateOfferDetails();
        } catch (InvalidOfferDetailsFileException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void populateOfferDetails() throws InvalidOfferDetailsFileException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("offer-coupons.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            boolean hasLines = true;
            int count = 0;
            while (hasLines) {
                String line = br.readLine();
                if(line != null) {
                    if (count != 0) {
                        String[] offerArguments = line.split(",");
                        OfferDetails offerDetails = new OfferDetails(offerArguments[0], offerArguments[1],
                                offerArguments[4], offerArguments[5], offerArguments[2], offerArguments[3]);
                        System.out.println("offer details object is "+offerDetails);
                        offerDetailsMap.put(offerDetails.getCouponId(), offerDetails);
                    }
                } else {
                    hasLines = false ;
                }
                count += 1;
            }

        } catch (IOException ex) {
            throw new InvalidOfferDetailsFileException(ex.getMessage());
        }
    }

    ;

    public static CostEstimationService getInstance() {
        if (costEstimationService == null) {
            costEstimationService = new CostEstimationService();
        }
        return costEstimationService;
    }
}
