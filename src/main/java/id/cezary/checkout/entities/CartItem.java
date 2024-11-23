package id.cezary.checkout.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class CartItem {
    String productName;
    int quantity;
}