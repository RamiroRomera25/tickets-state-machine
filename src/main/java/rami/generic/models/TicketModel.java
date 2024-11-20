package rami.generic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rami.generic.entities.ClientEntity;
import rami.generic.entities.TravelEntity;
import rami.generic.entities.base.BaseEntity;
import rami.generic.enums.TicketClass;
import rami.generic.enums.TicketStatus;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tickets")
public class TicketModel extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal price;

    @Column
    private TicketStatus status;

    @Column
    private TicketClass clazz;

    @ManyToOne
    private TravelEntity travel;

    @ManyToOne
    private ClientEntity client;
}
