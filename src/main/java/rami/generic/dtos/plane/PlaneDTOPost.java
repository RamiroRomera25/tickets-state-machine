package rami.generic.dtos.plane;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rami.generic.entities.AirlineEntity;
import rami.generic.entities.TicketEntity;
import rami.generic.entities.base.BaseEntity;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaneDTOPost {

    @Column
    private BigInteger planeNumber;

    @ManyToOne
    private Long airlineId;
}