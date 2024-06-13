package command;

import dto.PackageDetails;
import dto.ShipmentParameters;
import service.CostEstimationService;
import service.ShipmentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstimateTimeCommand {

    private EstimateTimeCommand() {}

    private static EstimateTimeCommand estimateTimeCommand;

    public static EstimateTimeCommand getInstance() {
        if(estimateTimeCommand == null) {
            estimateTimeCommand = new EstimateTimeCommand();
        }
        return estimateTimeCommand;
    }

    public void getResults(double baseCost, List<PackageDetails> packageDetailsList,
                             ShipmentParameters shipmentParameters) {
        List<PackageDetails> clonedPackageDetails = new ArrayList<>(packageDetailsList);
        CostEstimationService.getInstance().calculateCost(baseCost, packageDetailsList);
        Map<String, PackageDetails> resultsMap = new HashMap<>();
        ShipmentService.getInstance().calculateDeliveryTime(shipmentParameters, packageDetailsList, resultsMap);
        for(int resultIdx =0; resultIdx < clonedPackageDetails.size(); resultIdx++) {
            PackageDetails unEstimatedPackage = clonedPackageDetails.get(resultIdx);
            PackageDetails result = resultsMap.get(unEstimatedPackage.getPackageId());
            System.out.println(result.getPackageId() + " " + result.getDiscount() + " "
                    + result.getNetCost()  + " " + result.getTimeInHours());
        }
    }
}
