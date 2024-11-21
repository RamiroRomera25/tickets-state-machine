package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rami.generic.dtos.plane.PlaneDTOPost;
import rami.generic.entities.PlaneEntity;
import rami.generic.entities.TicketEntity;
import rami.generic.entities.TravelEntity;
import rami.generic.enums.TicketStatus;
import rami.generic.models.PlaneModel;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.PlaneRepository;
import rami.generic.services.PlaneService;

@Service
public class PlaneServiceImpl implements
        PlaneService<PlaneEntity, Long, PlaneModel, PlaneDTOPost> {

    @Autowired
    private PlaneRepository planeRepository;

    @Autowired @Qualifier("strictMapper")
    private ModelMapper mapper;

    @Autowired
    private TicketServiceImpl ticketService;

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

    @Override
    public GenericRepository<PlaneEntity, Long> getRepository() {
        return planeRepository;
    }

    @Override
    public Class entityClass() {
        return PlaneEntity.class;
    }

    @Override
    public Class modelClass() {
        return PlaneModel.class;
    }

    @Override
    public PlaneModel delete(Long planeId) {
        PlaneEntity plane = this.getById(planeId);

        for (TravelEntity travel : plane.getTravels()) {
            for (TicketEntity ticket : travel.getTickets()) {
                if (ticket.getStatus() == TicketStatus.PAID) {
                    ticketService.refundTicket(ticket.getId());
                } else if (ticket.getStatus() == TicketStatus.RESERVED) {
                    ticketService.cancelTicket(ticket.getId());
                }
            }
        }

        plane.setIsActive(false);

        return mapper.map(planeRepository.save(plane), PlaneModel.class);
    }
}
