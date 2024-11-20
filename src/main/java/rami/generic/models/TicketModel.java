package rami.generic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
