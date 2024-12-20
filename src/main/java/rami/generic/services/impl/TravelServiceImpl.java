package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rami.generic.dtos.travel.TravelDTOPost;
import rami.generic.entities.TicketEntity;
import rami.generic.entities.TravelEntity;
import rami.generic.enums.TicketStatus;
import rami.generic.models.TravelModel;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.TravelRepository;
import rami.generic.services.TravelService;

@Service
public class TravelServiceImpl implements
        TravelService<TravelEntity, Long, TravelModel, TravelDTOPost> {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired @Qualifier("strictMapper")
    private ModelMapper mapper;

    @Autowired
    private TicketServiceImpl ticketService;

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

    @Override
    public GenericRepository<TravelEntity, Long> getRepository() {
        return travelRepository;
    }

    @Override
    public Class entityClass() {
        return TravelEntity.class;
    }

    @Override
    public Class modelClass() {
        return TravelModel.class;
    }


    @Override
    public TravelModel delete(Long travelId) {
        TravelEntity travel = this.getById(travelId);

        for (TicketEntity ticket : travel.getTickets()) {
            if (ticket.getStatus() == TicketStatus.PAID) {
                ticketService.refundTicket(ticket.getId());
            } else if (ticket.getStatus() == TicketStatus.RESERVED) {
                ticketService.cancelTicket(ticket.getId());
            }
        }

        return mapper.map(travelRepository.save(travel), TravelModel.class);
    }
}
