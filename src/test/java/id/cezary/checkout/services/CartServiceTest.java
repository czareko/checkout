package id.cezary.checkout.services;

import id.cezary.checkout.entities.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private DBService dbService;


    @Test
    public void shouldStartNewCart() {
        UUID newCartId = cartService.startNew();
        Optional<Cart> cart = cartService.findCartById(newCartId);

        assertTrue(cart.isPresent());
    }


    @Test
    public void shouldCalculateCorrectTotalPrice(){
        //when:
        //Start a new cart
        UUID newCartId = cartService.startNew();
        Optional<Cart> cart = cartService.findCartById(newCartId);
        //given:
        //Add two items - no special price
        cartService.scan(newCartId,"A");
        cartService.scan(newCartId,"A");
        assertEquals(new BigDecimal("100"), cartService.calculateTotal(newCartId));

        //then:
        //Add third product - apply price rule
        cartService.scan(newCartId,"A");
        assertEquals(new BigDecimal("130"), cartService.calculateTotal(newCartId));
    }


}
