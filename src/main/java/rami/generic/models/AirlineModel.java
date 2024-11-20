package rami.generic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirlineModel {
    private Long id;

    private String name;

    private BigInteger canceledFlights;

    private BigInteger successFlights;

    private BigDecimal percentageForRefund;

    private BigDecimal totalRaised;
}
