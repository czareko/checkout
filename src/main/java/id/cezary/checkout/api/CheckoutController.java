package id.cezary.checkout.api;

import id.cezary.checkout.entities.PriceRule;
import id.cezary.checkout.entities.Product;
import id.cezary.checkout.services.DBService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
public class CheckoutController {

    private final DBService dbService;


    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return dbService.findAllProducts();
    }

    @GetMapping("/product/{name}")
    public Product getProduct(@PathVariable String name) {
        return dbService.findProductByName(name).get();
    }

    @GetMapping("/product/{name}/priceRules")
    public List<PriceRule> getPriceRulesForProduct(@PathVariable String name) {
        return dbService.findPriceRulesByProductName(name);
    }

}
