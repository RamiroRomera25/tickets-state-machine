package rami.generic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rami.generic.entities.base.BaseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "airlines")
public class AirlineEntity extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigInteger canceledFlights = BigInteger.ZERO;

    @Column
    private BigInteger successFlights = BigInteger.ZERO;

    @Column
    private BigDecimal totalRaised = BigDecimal.ZERO;

    @Column
    private BigDecimal percentageForRefund;
}
