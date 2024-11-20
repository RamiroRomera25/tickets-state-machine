package rami.generic.dtos.plane;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaneDTOPost {

    @Column
    private BigInteger planeNumber;

    @ManyToOne
    private Long airlineId;
}
