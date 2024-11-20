package rami.generic.dtos.airline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirlineDTOPost {
    private String name;

    private BigDecimal percentageForRefund;
}
