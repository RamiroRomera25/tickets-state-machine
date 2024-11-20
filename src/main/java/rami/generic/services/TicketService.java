package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.entities.base.BaseEntity;
import rami.generic.models.TicketModel;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;

@Service
public interface TicketService<E extends BaseEntity, I, M, DTOPOST>
        extends GenericGetAllList<E, I, M>,
        GenericCreate<E, I, M, DTOPOST>,
        GenericGetById<E, I, M> {

    TicketModel payTicket(Long ticketId);
    TicketModel cancelTicket(Long ticketId);
    TicketModel refundTicket(Long ticketId);
}
