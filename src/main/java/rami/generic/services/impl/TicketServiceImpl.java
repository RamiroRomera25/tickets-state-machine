package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rami.generic.dtos.ticket.TicketDTOPost;
import rami.generic.entities.TicketEntity;
import rami.generic.models.TicketModel;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.TicketRepository;
import rami.generic.services.TicketService;

@Service
public class TicketServiceImpl implements
        TicketService<TicketEntity, Long, TicketModel, TicketDTOPost> {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired @Qualifier("strictMapper")
    private ModelMapper mapper;

    @Override
    public Class<TicketEntity> entityClass() {
        return TicketEntity.class;
    }

    @Override
    public Class<TicketModel> modelClass() {
        return TicketModel.class;
    }

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

    @Override
    public GenericRepository<TicketEntity, Long> getRepository() {
        return ticketRepository;
    }

    @Override
    public TicketModel payTicket(Long ticketId) {
        return null;
    }

    @Override
    public TicketModel cancelTicket(Long ticketId) {
        return null;
    }

    @Override
    public TicketModel refundTicket(Long ticketId) {
        return null;
    }

    @Override
    public TicketModel completeTicket(Long ticketId) {
        return null;
    }
}
