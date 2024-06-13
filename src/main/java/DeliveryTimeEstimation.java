import command.EstimateTimeCommand;
import dto.PackageDetails;
import dto.PackageParameters;
import dto.ShipmentParameters;
import exception.IncompletePackageDetailsException;
import java.util.*;

/**
 * @author Mahesh T
 * <p>This Class is used to calculate the delivery time for the Kiki's Courier Service</p>
 */
public class DeliveryTimeEstimation {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the base delivery cost and number of packages in the following format.");
        System.out.println("base delivery cost\nnumber of packages");
        double baseDeliveryCost = scanner.nextDouble();
        int numberOfPackages = scanner.nextInt();
        PackageParameters packageParameters = new PackageParameters(numberOfPackages, baseDeliveryCost);
        System.out.println("the values entered are " + baseDeliveryCost + " and package count is "
                + numberOfPackages);
        try {
            List<PackageDetails> packageDetailsContainer = new ArrayList<>();
            for(int packageIndex = 0; packageIndex < packageParameters.getPackageCount(); packageIndex ++) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the details of package "+( packageIndex + 1) + " in following format.");
                System.out.println("PackageId WeightInKG DistanceInKM OfferCouponId");
                String details = scan.nextLine();
                String[] packageArray = details.split(" ");
                PackageDetails packageDetails = null;
                try {
                    packageDetails = new PackageDetails(packageArray[0], Double.parseDouble(packageArray[1]),
                            Double.parseDouble(packageArray[2]), packageArray[3]);
                } catch (IndexOutOfBoundsException ex) {
                    throw new IncompletePackageDetailsException(details + " is not a valid package detail.");
                }
                packageDetailsContainer.add(packageDetails);
            }
            ShipmentParameters shipmentParams = getShipmentParams();
            System.out.println("the value of shipment details : " + shipmentParams.toString());
            System.out.println("the size of entered list is "+packageDetailsContainer.size());
            EstimateTimeCommand.getInstance().getResults(packageParameters.getBaseCost(),
                    packageDetailsContainer, shipmentParams);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("***Bye***");
        }
    }


    private static ShipmentParameters getShipmentParams() {
        ShipmentParameters shipmentParams = null;
        Scanner shipmentScanner = new Scanner(System.in);
        System.out.println("Enter the details of number of vehicles, max_speed, max weight in the following format");
        System.out.println("number_of_vehicles max_speed max_weight");
        String shipmentInput = shipmentScanner.nextLine();
        String[] shipmentDetailsArray = shipmentInput.split(" ");

        for(int shipmentIdx = 0; shipmentIdx < shipmentDetailsArray.length; shipmentIdx ++) {
            shipmentParams = new ShipmentParameters();
            shipmentParams.setVehiclesCount(Integer.parseInt(shipmentDetailsArray[0]));
            shipmentParams.setMaxSpeed(Double.parseDouble(shipmentDetailsArray[1]));
            shipmentParams.setMaxWeight(Double.parseDouble(shipmentDetailsArray[2]));
        }
        return shipmentParams;
    }
}