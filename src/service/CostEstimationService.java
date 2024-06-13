package service;

import dto.PackageDetails;
import exception.InvalidCouponIdException;
import offerTemplate.OfferCoupon;
import offerTemplate.factory.OfferCouponFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CostEstimationService {
    private  static CostEstimationService costEstimationService = null;
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
        OfferCouponFactory offerCouponFactory = new OfferCouponFactory();
        OfferCoupon offerCoupon = offerCouponFactory.getValidOfferCoupon(packageDetails.getCouponId());
        if(offerCoupon != null ) {
            if(offerCoupon.isOfferApplicable(packageDetails.getDistance(),
                    packageDetails.getWeight())) {
                discount = totalCost * offerCoupon.getDiscount();
            } else {
                System.out.println("Offer Not applicable for the given package details \n" + packageDetails.toString());
            }
        } else {
            throw new InvalidCouponIdException(packageDetails.getCouponId() + " is not a valid coupon id");
        }
        return discount;
    }

    private CostEstimationService() {};

    public static CostEstimationService getInstance() {
        if (costEstimationService == null) {
            costEstimationService = new CostEstimationService();
        }
        return costEstimationService;
    }
}
