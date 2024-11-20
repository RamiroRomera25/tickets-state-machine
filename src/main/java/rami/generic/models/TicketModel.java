package rami.generic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rami.generic.entities.ClientEntity;
import rami.generic.entities.TravelEntity;
import rami.generic.entities.base.BaseEntity;
import rami.generic.enums.TicketClass;
import rami.generic.enums.TicketStatus;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketModel {

    private Long id;

    private BigDecimal price;

    private TicketStatus status;

    private TicketClass clazz;

    private Long travelId;

    private Long clientId;
}
