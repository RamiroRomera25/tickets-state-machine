package rami.generic.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rami.generic.entities.base.BaseEntity;

import java.math.BigInteger;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "planes")
public class PlaneEntity extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigInteger planeNumber;

    @Column
    private BigInteger capacity;

    @ManyToOne
    private AirlineEntity airline;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;
}
