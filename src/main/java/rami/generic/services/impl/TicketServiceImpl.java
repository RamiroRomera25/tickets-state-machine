package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.dtos.ticket.TicketDTOPost;
import rami.generic.entities.AirlineEntity;
import rami.generic.entities.PlaneEntity;
import rami.generic.entities.TicketEntity;
import rami.generic.entities.TravelEntity;
import rami.generic.enums.TicketStatus;
import rami.generic.models.TicketModel;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.TicketRepository;
import rami.generic.services.TicketService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TicketServiceImpl implements
        TicketService<TicketEntity, Long, TicketModel, TicketDTOPost> {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired @Qualifier("strictMapper")
    private ModelMapper mapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private TravelServiceImpl travelService;

    //#region Generic Override
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
    //#endregion

    @Override
    public GenericRepository<TicketEntity, Long> getRepository() {
        return ticketRepository;
    }

    @Override
    public TicketModel create(TicketDTOPost dtoPost) {
        TicketEntity newTicket = new TicketEntity();

        TravelEntity travel = travelService.getById(dtoPost.getTravelId());

        PlaneEntity plane = travel.getPlane();

        if (plane.getCapacity().compareTo(BigInteger.valueOf(travel.getTickets().size())) < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The plane is full of capacity.");
        }

        newTicket.setClient(clientService.getById(dtoPost.getClientId()));

        newTicket.setTravel(travel);
        newTicket.setClazz(dtoPost.getClazz());

        switch (newTicket.getClazz()) {
            case ECONOMY -> newTicket.setPrice(newTicket.getTravel().getPriceForEconomy());
            case FIRST -> newTicket.setPrice(newTicket.getTravel().getPriceForFirstClass());
        }

        return mapper.map(ticketRepository.save(newTicket), TicketModel.class);
    }

    @Override
    public TicketModel payTicket(Long ticketId) {
        TicketEntity ticket = this.getById(ticketId);

        if (ticket.getStatus() != TicketStatus.RESERVED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "We accept donations but not into this way. :)");
        }

        PlaneEntity plane = ticket.getTravel().getPlane();

        ticket.setStatus(TicketStatus.PAID);

        AirlineEntity airline = plane.getAirline();

        airline.setTotalRaised(airline.getTotalRaised().add(ticket.getPrice()));

        return mapper.map(ticketRepository.save(ticket), TicketModel.class);
    }

    @Override
    public TicketModel cancelTicket(Long ticketId) {
        TicketEntity ticket = this.getById(ticketId);

        if (ticket.getStatus() != TicketStatus.RESERVED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Only can cancel tickets reserved. To get a refund go to refund method.");
        }

        ticket.setStatus(TicketStatus.CANCELLED);
        ticket.setIsActive(false);

        return mapper.map(ticketRepository.save(ticket), TicketModel.class);
    }

    @Override
    public TicketModel refundTicket(Long ticketId) {
        TicketEntity ticket = this.getById(ticketId);

        if (ticket.getStatus() != TicketStatus.PAID) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't refund something that didn't pay. :)");
        }

        TravelEntity travel = ticket.getTravel();

        Long dayDiff = ChronoUnit.DAYS.between(LocalDateTime.now(), travel.getStartDate());

        ticket.setStatus(TicketStatus.REFUND);
        ticket.setIsActive(false);

        AirlineEntity airline = ticket.getTravel().getPlane().getAirline();

        BigDecimal totalPercentageForRefund =
                airline.getPercentageForRefund()
                .multiply(BigDecimal.valueOf(dayDiff))
                .multiply(BigDecimal.valueOf(100L));

        BigDecimal totalToKeep = ticket.getPrice().multiply(totalPercentageForRefund);

        airline.setTotalRaised(airline.getTotalRaised().subtract(ticket.getPrice().subtract(totalToKeep)));

        return mapper.map(ticketRepository.save(ticket), TicketModel.class);
    }

    @Override
    public TicketModel completeTicket(Long ticketId) {
        TicketEntity ticket = this.getById(ticketId);

        if (ticket.getStatus() == TicketStatus.CANCELLED || ticket.getStatus() == TicketStatus.REFUND) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't confirm a ticket cancelled or refund. :)");
        }

        ticket.setStatus(TicketStatus.COMPLETED);

        return mapper.map(ticketRepository.save(ticket), TicketModel.class);
    }
}
