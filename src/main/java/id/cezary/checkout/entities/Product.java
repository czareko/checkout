package id.cezary.checkout.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
        UUID id,
        String name,
        BigDecimal price
) {}
