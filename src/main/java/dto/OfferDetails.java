package dto;

public class OfferDetails {

    public OfferDetails() {}

    public String toString() {
        return "coupon : " + this.couponId + " discount: "+ this.discount + " minWeight" + this.minimumWeight
        + " maxWeight: "+ this.maximumWeight + " minDistance : " + this.minimumDistance +
                " maxDistance : "+ this.maximumDistance;
    }

    public OfferDetails(String couponId, String discountPercent, String minWeight, String maxWeight,
                        String minDistance, String maxDistance) {
        this.couponId = couponId;
        double discountPcnt = Double.parseDouble(discountPercent);
        this.discount = discountPcnt/100;
        this.minimumWeight = Double.parseDouble(minWeight);
        this.maximumWeight = Double.parseDouble(maxWeight);
        this.minimumDistance = Double.parseDouble(minDistance);
        this.maximumDistance = Double.parseDouble(maxDistance);
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getMinimumDistance() {
        return minimumDistance;
    }

    public void setMinimumDistance(Double minimumDistance) {
        this.minimumDistance = minimumDistance;
    }

    public Double getMaximumDistance() {
        return maximumDistance;
    }

    public void setMaximumDistance(Double maximumDistance) {
        this.maximumDistance = maximumDistance;
    }

    public Double getMinimumWeight() {
        return minimumWeight;
    }

    public void setMinimumWeight(Double minimumWeight) {
        this.minimumWeight = minimumWeight;
    }

    public Double getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(Double maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    private String couponId;

    private Double discount;

    private Double minimumDistance;

    private Double maximumDistance;

    private Double minimumWeight;

    private Double maximumWeight;
}
