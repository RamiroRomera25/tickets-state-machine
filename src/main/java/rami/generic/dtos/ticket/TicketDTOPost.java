package rami.generic.dtos.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rami.generic.enums.TicketClass;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTOPost {
    private TicketClass clazz;

    private Long travelId;

    private Long clientId;
}
