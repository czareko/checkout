package id.cezary.checkout.services;

import id.cezary.checkout.entities.PriceRule;
import id.cezary.checkout.entities.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DBServiceImpl implements DBService{

    private final Map<UUID, Product> productMap = new HashMap<>();
    private final Map<UUID, PriceRule> priceRuleMap = new HashMap<>();

    public DBServiceImpl() {
        initDB();
    }

    private void initDB(){
        Product productA = new Product(UUID.randomUUID(), "A", new BigDecimal(50));
        Product productB = new Product(UUID.randomUUID(), "B", new BigDecimal(30));
        Product productC = new Product(UUID.randomUUID(), "C", new BigDecimal(20));
        Product productD = new Product(UUID.randomUUID(), "D", new BigDecimal(15));

        PriceRule priceRuleA = new PriceRule(UUID.randomUUID(), productA.id(), 3, new BigDecimal(130));
        PriceRule priceRuleB = new PriceRule(UUID.randomUUID(), productB.id(), 2, new BigDecimal(45));

        addProduct(productA);
        addProduct(productB);
        addProduct(productC);
        addProduct(productD);

        addPriceRule(priceRuleA);
        addPriceRule(priceRuleB);
    }

    public void addProduct(Product product) {
        productMap.put(product.id(), product);
    }

    public void removeProduct(UUID id) {
        removePriceRulesByProductId(id);
        productMap.remove(id);
    }

    public void addPriceRule(PriceRule priceRule) {
        priceRuleMap.put(priceRule.id(), priceRule);
    }

    public void removePriceRule(PriceRule priceRule) {
        priceRuleMap.remove(priceRule.id());
    }

    public void removePriceRulesByProductId(UUID productId) {
        priceRuleMap.values().removeIf(priceRule -> priceRule.productId().equals(productId));
    }

    public List<Product> findAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public Product findProductById(UUID productId) {
        return productMap.get(productId);
    }
    public List<Product> findProductByName(String name) {
        return productMap.values().stream()
                .filter(product -> product.name().equals(name))
                .collect(Collectors.toList());
    }

    public List<PriceRule> findPriceRulesByProductId(UUID productId) {
        return priceRuleMap.values().stream()
                .filter(priceRule -> priceRule.productId().equals(productId))
                .collect(Collectors.toList());
    }

    public List<PriceRule> findPriceRulesByProductName(String productName) {
        List<Product> products = findProductByName(productName);
        return products.stream()
                .flatMap(product -> findPriceRulesByProductId(product.id()).stream())
                .collect(Collectors.toList());
    }
}
