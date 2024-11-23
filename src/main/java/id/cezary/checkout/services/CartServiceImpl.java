package id.cezary.checkout.services;

import id.cezary.checkout.entities.Cart;
import id.cezary.checkout.entities.CartItem;
import id.cezary.checkout.entities.PriceRule;
import id.cezary.checkout.entities.Product;
import id.cezary.checkout.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final Map<UUID, Cart> cartMap = new HashMap<>();

    private final DBService dbService;

    @Override
    public UUID startNew() {
        UUID cartId = UUID.randomUUID();
        cartMap.put(cartId, new Cart(cartId, new ArrayList<>()));
        return cartId;
    }

    @Override
    public void scan(UUID checkoutId, String productName) throws ProductNotFoundException {
        Optional<Product> product = dbService.findProductByName(productName);
        if(product.isPresent()) {
            Optional<CartItem> cartItem = cartMap.get(checkoutId).items().stream()
                    .filter(item -> productName.equals(item.getProductName())).findFirst();
            if(cartItem.isPresent()) {
                cartMap.get(checkoutId).items().removeIf(item -> productName.equals(item.getProductName()));
                cartItem.get().setQuantity(cartItem.get().getQuantity()+1);
                cartMap.get(checkoutId).items().add(cartItem.get());
            }
            else{
                cartMap.get(checkoutId).items().add(CartItem.builder()
                        .productName(product.get().name()).quantity(1).build());
            }
        }
        else
            throw new ProductNotFoundException(productName);
    }

    @Override
    public Optional<Cart> findCartById(UUID id) {
        return Optional.ofNullable(cartMap.get(id));
    }

    @Override
    public BigDecimal calculateTotal(UUID checkoutId) {

        Cart cart = cartMap.get(checkoutId);
        return cart.items().stream()
                .map(this::calculatePriceForItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calculatePriceForItem(CartItem item)  {
        String productName = item.getProductName();
        int quantity = item.getQuantity();

        Optional<Product> productOpt = dbService.findProductByName(productName);
        if(productOpt.isPresent()){
            Product product = productOpt.get();
            Optional<PriceRule> priceRuleOpt = dbService
                    .findPriceRuleByProductNameAndQuantity(product.name(), quantity);

            if(priceRuleOpt.isPresent()){
                return priceRuleOpt.get().price();
            }
            else {
                return product.price().multiply(BigDecimal.valueOf(quantity));
            }
        }
        return BigDecimal.ZERO; // return 0 if product not found
    }
}
