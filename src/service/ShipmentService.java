package service;

import dto.PackageDetails;
import dto.ShipmentDetails;
import dto.ShipmentParameters;
import utils.sort.PackageDetailsComparator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * <p> ShipmentService calculates the estimated deliver time for each package considering speed, distance, waiting time
 * </p>
 */
public class ShipmentService {

    private static ShipmentService shipmentService = null;

    private ShipmentService() {
    }

    public static ShipmentService getInstance() {
        if (shipmentService == null) {
            shipmentService = new ShipmentService();
        }
        return shipmentService;
    }

    public void calculateDeliveryTime(ShipmentParameters shipmentParameters, List<PackageDetails> packageDetailsList,
                                      Map<String, PackageDetails> resultsMap) {
        Map<String, PackageDetails> packageMap = new LinkedHashMap<>();
        Collections.sort(packageDetailsList, new PackageDetailsComparator());
        List<List<PackageDetails>> shipments = new ArrayList<>();
        List<PackageDetails> presentShipments = new ArrayList<>();
        List<Double> shipmentWeightList = new ArrayList<>();
        double currentWeight = 0;
        shipments = getShipmentsList(shipmentParameters, packageDetailsList, packageMap, shipments,
                presentShipments, shipmentWeightList, currentWeight);
        int shipmentSize = shipments.size();
        int shipmentIndex = 0;
        Map<String, Double> shipmentWaitTime = new HashMap<>();
        List<ShipmentDetails> shipmentsList = new ArrayList<>();

        while (shipmentIndex < shipmentSize) {
            if(!shipmentWaitTime.isEmpty()) {
                Map<String, Double> sortedMap = sortByValue(shipmentWaitTime);
                String vehicleId = "";
                for(Map.Entry<String, Double> firstEntry : sortedMap.entrySet()) {
                    vehicleId = firstEntry.getKey();
                    break;
                }
                double previousShipmentTime = shipmentWaitTime.getOrDefault(vehicleId, 0.0);
                List<PackageDetails> packageDetails = shipments.get(shipmentIndex);
                shipmentIndex += 1;
                double maxTime = 0;
                for (PackageDetails pckgDtls : packageDetails) {
                    double time = pckgDtls.getDistance() / shipmentParameters.getMaxSpeed();
                    time = new BigDecimal(time).setScale(2, RoundingMode.FLOOR).doubleValue();
                    if (time > maxTime)
                        maxTime = time;
                    pckgDtls.setTimeInHours((previousShipmentTime + time));
                    resultsMap.put(pckgDtls.getPackageId(), pckgDtls);
                }
                double waitingPeriod = previousShipmentTime + ( maxTime * 2 );
                ShipmentDetails shipment =
                        new ShipmentDetails(vehicleId, packageDetails, waitingPeriod);
                shipmentWaitTime.put(vehicleId, (new BigDecimal(waitingPeriod).
                        setScale(2, RoundingMode.FLOOR).doubleValue()));
                shipmentsList.add(shipment);
            } else {
                for (int vehicleIndex = 0; vehicleIndex < shipmentParameters.getVehiclesCount(); vehicleIndex++) {
                    String vehicleId = "v" + (vehicleIndex + 1);
                    List<PackageDetails> packageDetails = shipments.get(shipmentIndex);
                    shipmentIndex += 1;
                    double maxTime = 0;
                    for (PackageDetails pckgDtls : packageDetails) {
                        double time = pckgDtls.getDistance() / shipmentParameters.getMaxSpeed();
                        time = new BigDecimal(time).setScale(2, RoundingMode.FLOOR).doubleValue();
                        if (time > maxTime)
                            maxTime = time;
                        pckgDtls.setTimeInHours(time);
                        resultsMap.put(pckgDtls.getPackageId(), pckgDtls);
                    }
                    double waitingPeriod = maxTime * 2;
                    ShipmentDetails shipment =
                            new ShipmentDetails(vehicleId, packageDetails, waitingPeriod);
                    shipmentWaitTime.put(vehicleId, waitingPeriod);
                    shipmentsList.add(shipment);
                }
            }
        }
    }
    private List<List<PackageDetails>> getShipmentsList(ShipmentParameters shipmentParameters, List<PackageDetails> packageDetailsList, Map<String, PackageDetails> packageMap, List<List<PackageDetails>> shipments, List<PackageDetails> presentShipments, List<Double> shipmentWeightList, double currentWeight) {
        for (PackageDetails packageDetails : packageDetailsList) {
            packageMap.put(packageDetails.getPackageId(), packageDetails);
            if(currentWeight + packageDetails.getWeight() <= shipmentParameters.getMaxWeight()) {
                presentShipments.add(packageDetails);
                currentWeight += packageDetails.getWeight();
            } else {
                shipments.add(presentShipments);
                shipmentWeightList.add(currentWeight);
                presentShipments = new ArrayList<>();
                presentShipments.add(packageDetails);
                currentWeight = packageDetails.getWeight();
            }
        }
        if (presentShipments.size() > 0) {
            shipments.add(presentShipments);
            shipmentWeightList.add(currentWeight);
        }
        shipmentWeightList.sort(Comparator.reverseOrder());
        //System.out.println("the value of sorted shipment weights are : "+ shipmentWeightList);
        // sort the shipments list based on its weight list
        List<List<PackageDetails>> sortedShipments = new ArrayList<>(shipments);
        for(int shpIdx = 0; shpIdx < shipments.size(); shpIdx++) {
            List<PackageDetails> packageDetails = shipments.get(shpIdx);
            double pkgWeight = 0;
            for (PackageDetails packageDetail: packageDetails) {
                pkgWeight += packageDetail.getWeight();
            }
            int index = shipmentWeightList.indexOf(pkgWeight);
            sortedShipments.set(index, packageDetails);
        }
//        System.out.println("the value of shipments are : "
//                + sortedShipments.toString() + " and its corresponding weights are " + shipmentWeightList);
        return sortedShipments;
    }

    public HashMap<String, Double> sortByValue(Map<String, Double> hm)
    {
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
