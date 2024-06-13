package utils.sort;

import dto.PackageDetails;

import java.util.Comparator;

public class PackageDetailsComparator implements Comparator<PackageDetails> {

    @Override
    public int compare(PackageDetails o1, PackageDetails o2) {
        int weightComparison = Double.compare(o2.getWeight(), o1.getWeight());
        return weightComparison != 0 ? weightComparison : Double.compare(o1.getDistance(), o2.getDistance());
    }
}
