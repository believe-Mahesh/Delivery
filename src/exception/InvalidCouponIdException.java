package exception;

/**
 * <p>This Exception is thrown when the invalid coupon id is passes and if this coupon id is not able to
 * instantiate a valid OfferCoupon Object</p>
 */
public class InvalidCouponIdException extends RuntimeException {

    public InvalidCouponIdException() {

    };

    public InvalidCouponIdException(String message) {
        super(message);
    }
}
