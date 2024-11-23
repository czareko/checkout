package id.cezary.checkout.services;

import id.cezary.checkout.entities.Cart;
import id.cezary.checkout.entities.CartItem;
import id.cezary.checkout.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CartService {

    UUID startNew();
    void scan(UUID checkoutId, String productName) throws ProductNotFoundException;
    Optional<Cart> findCartById(UUID id);
    BigDecimal calculateTotal(UUID checkoutId);
    BigDecimal calculatePriceForItem(CartItem item);
}
