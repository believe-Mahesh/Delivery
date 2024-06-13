package dto;

public class PackageParameters {

    public Integer getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(Integer packageCount) {
        this.packageCount = packageCount;
    }

    public Double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Double baseCost) {
        this.baseCost = baseCost;
    }

    private Integer packageCount;

    private Double baseCost;

    public PackageParameters() {

    }

    public PackageParameters (Integer packageCount, Double baseCost) {
        this.packageCount = packageCount;
        this.baseCost = baseCost;
    }
}
