package id.cezary.checkout.services;

import id.cezary.checkout.entities.PriceRule;
import id.cezary.checkout.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public interface DBService {

    void addProduct(Product product);

    void removeProduct(UUID id);

    void addPriceRule(PriceRule priceRule);

    void removePriceRule(PriceRule priceRule);

    void removePriceRulesByProductId(UUID productId);

    List<Product> findAllProducts();

    Optional<Product> findProductById(UUID productId);

    Optional<Product> findProductByName(String name);

    List<PriceRule> findPriceRulesByProductId(UUID productId);

    List<PriceRule> findPriceRulesByProductName(String productName);

    Optional<PriceRule> findPriceRuleByProductNameAndQuantity(String productName, Integer quantity);
}
