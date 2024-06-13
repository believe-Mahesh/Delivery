package offerTemplate.factory;

import offerTemplate.OfferCoupon;
import offerTemplate.OfferCoupon1;
import offerTemplate.OfferCoupon2;
import offerTemplate.OfferCoupon3;

public class OfferCouponFactory {

    public OfferCoupon getValidOfferCoupon(String couponId) {
        OfferCoupon offerCoupon = null;
        switch (couponId) {
            case "OFR001":
                offerCoupon = new OfferCoupon1();
                break;
            case "OFR002":
                offerCoupon = new OfferCoupon2();
                break;
            case "OFR003":
                offerCoupon = new OfferCoupon3();
                break;
        }
        return offerCoupon;
    }
}
