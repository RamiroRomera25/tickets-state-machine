package rami.generic.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rami.generic.entities.base.BaseEntity;
import rami.generic.enums.TicketClass;
import rami.generic.enums.TicketStatus;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tickets")
public class TicketEntity extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal price;

    @Column
    private TicketStatus status = TicketStatus.RESERVED;

    @Column
    private TicketClass clazz;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TravelEntity travel;

    @ManyToOne
    private ClientEntity client;
}
