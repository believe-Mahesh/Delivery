package command;

import dto.PackageDetails;
import service.CostEstimationService;

import java.util.List;

public class EstimateCostCommand {

    private EstimateCostCommand() {

    }

    private static EstimateCostCommand estimateCostCommand = null;

    public static EstimateCostCommand getInstance() {
        if (estimateCostCommand == null) {
            estimateCostCommand = new EstimateCostCommand();
        }
        return estimateCostCommand;
    }

    public void getResults(double baseDeliveryCost, List<PackageDetails> packageDetailsList) {
        CostEstimationService.getInstance().calculateCost(baseDeliveryCost, packageDetailsList);
        for(PackageDetails packageDetails: packageDetailsList) {
            System.out.println(packageDetails.getPackageId() + " " + packageDetails.getDiscount() + " "
                    + packageDetails.getNetCost());
        }
    }
}
