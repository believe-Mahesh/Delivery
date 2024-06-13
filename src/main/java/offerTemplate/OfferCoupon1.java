package offerTemplate;

public class OfferCoupon1 extends OfferCoupon{
    @Override
    public Double getUpperPermissibleWeight() {
        return 200.0;
    }

    @Override
    public Double getLowerPermissibleWeight() {
        return 70.0;
    }

    @Override
    public Double getUpperPermissibleDistance() {
        return 200.0;
    }

    @Override
    public Double getLowerPermissibleDistance() {
        return 0.0;
    }

    @Override
    public Double getDiscount() {
        return 0.10;
    }
}
