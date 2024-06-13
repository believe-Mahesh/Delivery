package offerTemplate;

public abstract class OfferCoupon {

    public abstract Double getUpperPermissibleWeight();

    public abstract Double getLowerPermissibleWeight();

    public abstract Double getUpperPermissibleDistance();

    public abstract Double getLowerPermissibleDistance();

    private boolean isValidDistance(Double distance) {
        return ((Double.compare(distance, getLowerPermissibleDistance()) == 0
                || Double.compare(distance, getLowerPermissibleDistance()) == 1)
                &&
                (Double.compare(distance, getUpperPermissibleDistance()) == 0
                        || Double.compare(distance, getUpperPermissibleDistance()) == -1));
    }

    private boolean isValidWeight(Double weight) {
        return ((Double.compare(weight, getLowerPermissibleWeight()) == 0
                || Double.compare(weight, getLowerPermissibleWeight()) == 1)
                &&
                (Double.compare(weight, getUpperPermissibleWeight()) == 0
                        || Double.compare(weight, getUpperPermissibleWeight()) == -1));
    }

    public boolean isOfferApplicable(Double distance, Double weight) {
        return isValidDistance(distance) && isValidWeight(weight);
    }

    public abstract Double getDiscount();
}
