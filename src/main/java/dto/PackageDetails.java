package dto;

public class PackageDetails {

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getTimeInHours() {
        return timeInHours;
    }

    public void setTimeInHours(Double timeInHours) {
        this.timeInHours = timeInHours;
    }

    private String packageId;

    private String couponId;

    private Double weight;

    private Double distance;

    private String vehicleId;

    private Double timeInHours;

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getNetCost() {
        return netCost;
    }

    public void setNetCost(Double netCost) {
        this.netCost = netCost;
    }

    private Double totalCost;

    private Double discount;

    private Double netCost;


    public PackageDetails() {}

    public PackageDetails(String packageId, Double weight, Double distance, String couponId) {
        this.packageId = packageId;
        this.couponId = couponId;
        this.weight = weight;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "packageId:" + packageId + " weight:"+ weight + " distance:" + distance +
                " couponId:" + couponId + " timeInHrs: "+ timeInHours;
    }
}
