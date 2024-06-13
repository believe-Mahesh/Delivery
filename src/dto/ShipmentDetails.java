package dto;

import java.util.List;

public class ShipmentDetails {

    public ShipmentDetails() {}

    public ShipmentDetails(String vId, List<PackageDetails> dtls, Double waitingTime) {
        this.vehicleId = vId;
        this.packageDetails = dtls;
        this.waitingTime = waitingTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<PackageDetails> getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(List<PackageDetails> packageDetails) {
        this.packageDetails = packageDetails;
    }

    private String vehicleId;

    private List<PackageDetails> packageDetails;

    public Double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Double waitingTime) {
        this.waitingTime = waitingTime;
    }

    private Double waitingTime;


    @Override
    public String toString() {
        return "vehicleId: "+vehicleId + " packageDetails: "+packageDetails.toString() + " waitingTime: "+ waitingTime ;
    }
}
