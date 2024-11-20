package rami.generic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaneModel {
    private Long id;

    private BigInteger planeNumber;

    private Long airline;
}
