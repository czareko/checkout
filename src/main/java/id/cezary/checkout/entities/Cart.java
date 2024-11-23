package id.cezary.checkout.entities;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

public record Cart(
        UUID id,
        List<CartItem> items
) {}
