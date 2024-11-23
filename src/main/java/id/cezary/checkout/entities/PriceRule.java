package id.cezary.checkout.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

public record PriceRule (
    UUID id,
    UUID productId,
    Integer quantity,
    BigDecimal price
){}
