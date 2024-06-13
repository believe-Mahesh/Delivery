package exception;

/**
 * <p> This exception is thrown when the package details from cli does not return 4 elements in the string array
 * after splitting it with space.</p>
 */
public class IncompletePackageDetailsException extends RuntimeException {

    public IncompletePackageDetailsException() {

    }

    public IncompletePackageDetailsException(String msg) {
        super(msg);
    }
}
