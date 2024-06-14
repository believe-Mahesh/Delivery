package offerTemplate;

import dto.OfferDetails;

public class OfferCoupon {

    private double discount;
    private double upperPermissibleWeight;

    private double lowerPermissibleWeight;

    private double upperPermissibleDistance;

    private double lowerPermissibleDistance;

    public OfferCoupon() {}

    public OfferCoupon(OfferDetails offerDetails) {
        this.discount = offerDetails.getDiscount();
        this.upperPermissibleWeight = offerDetails.getMaximumWeight();
        this.lowerPermissibleWeight = offerDetails.getMinimumWeight();
        this.upperPermissibleDistance = offerDetails.getMaximumDistance();
        this.lowerPermissibleDistance = offerDetails.getMinimumDistance();
    }

    public Double getUpperPermissibleWeight() {
        return upperPermissibleWeight;
    }

    public Double getLowerPermissibleWeight() {
        return lowerPermissibleWeight;
    }

    public Double getUpperPermissibleDistance() {
        return upperPermissibleDistance;
    }

    public Double getLowerPermissibleDistance() {
        return lowerPermissibleDistance;
    }

    private boolean isValidDistance(Double distance) {
        return ((Double.compare(distance, getLowerPermissibleDistance()) == 0
                || Double.compare(distance, getLowerPermissibleDistance()) == 1)
                &&
                (Double.compare(distance, getUpperPermissibleDistance()) == 0
                        || Double.compare(distance, getUpperPermissibleDistance()) == -1));
    }

    private boolean isValidWeight(Double weight) {
        return ((Double.compare(weight, getLowerPermissibleWeight()) == 0
                || Double.compare(weight, getLowerPermissibleWeight()) == 1)
                &&
                (Double.compare(weight, getUpperPermissibleWeight()) == 0
                        || Double.compare(weight, getUpperPermissibleWeight()) == -1));
    }

    public boolean isOfferApplicable(Double distance, Double weight) {
        return isValidDistance(distance) && isValidWeight(weight);
    }

    public Double getDiscount() {
        return discount;
    }
}
