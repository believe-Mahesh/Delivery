package offerTemplate;

public class OfferCoupon3 extends OfferCoupon {
    @Override
    public Double getUpperPermissibleWeight() {
        return 150.0;
    }

    @Override
    public Double getLowerPermissibleWeight() {
        return 10.0;
    }

    @Override
    public Double getUpperPermissibleDistance() {
        return 250.0;
    }

    @Override
    public Double getLowerPermissibleDistance() {
        return 50.0;
    }

    @Override
    public Double getDiscount() {
        return 0.05;
    }
}
