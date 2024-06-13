package dto;

public class ShipmentParameters {

    public Integer getVehiclesCount() {
        return vehiclesCount;
    }

    public void setVehiclesCount(Integer vehiclesCount) {
        this.vehiclesCount = vehiclesCount;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    private Integer vehiclesCount;

    private Double maxSpeed;

    private Double maxWeight;

    @Override
    public String toString() {
        return "vehicleCount :" + this.vehiclesCount + " maxSpeed:" + this.maxSpeed + " maxWeight:" + this.maxWeight;
    }
}
