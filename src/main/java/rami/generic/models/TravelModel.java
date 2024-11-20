package rami.generic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TravelModel {

    private Long id;

    private LocalDateTime startDate;

    private BigDecimal priceForEconomy;

    private BigDecimal priceForFirstClass;

    private Long planeId;
}
