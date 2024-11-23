package id.cezary.checkout.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super("Product name: "+message);
    }
}
