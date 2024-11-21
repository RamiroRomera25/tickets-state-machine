package rami.generic.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.RandomDataForObject;
import rami.generic.dtos.ticket.TicketDTOPost;
import rami.generic.entities.ClientEntity;
import rami.generic.entities.TicketEntity;
import rami.generic.entities.TravelEntity;
import rami.generic.enums.TicketClass;
import rami.generic.enums.TicketStatus;
import rami.generic.models.TicketModel;
import rami.generic.repositories.TicketRepository;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TicketServiceImplTest {
    private TicketEntity ticket;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private TravelServiceImpl travelService;

    @MockBean
    private ClientServiceImpl clientService;

    @SpyBean
    private TicketServiceImpl ticketService;

    @BeforeEach
    public void setup() throws IllegalAccessException {
        ticket = RandomDataForObject.generateRandomValues(new TicketEntity());
    }

    @Test
    void createTicketTest_EconomyClazz() throws IllegalAccessException {
        TicketDTOPost post = RandomDataForObject.generateRandomValues(new TicketDTOPost());
        post.setClazz(TicketClass.ECONOMY);
        TravelEntity travel = RandomDataForObject.generateRandomValues(new TravelEntity());
        ClientEntity client = RandomDataForObject.generateRandomValues(new ClientEntity());

        when(travelService.getById(post.getTravelId())).thenReturn(travel);
        when(clientService.getById(post.getClientId())).thenReturn(client);
        ticket.setStatus(TicketStatus.RESERVED);
        when(ticketRepository.save(any())).thenReturn(ticket);

        TicketModel result = ticketService.create(post);

        assertEquals(TicketStatus.RESERVED, result.getStatus());
        assertEquals(ticket.getClazz() ,result.getClazz());
        assertEquals(ticket.getId(), result.getId());
        assertEquals(ticket.getPrice(), result.getPrice());
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void createTicketTest_FirstClazz() throws IllegalAccessException {
        TicketDTOPost post = RandomDataForObject.generateRandomValues(new TicketDTOPost());
        post.setClazz(TicketClass.FIRST);
        TravelEntity travel = RandomDataForObject.generateRandomValues(new TravelEntity());
        ClientEntity client = RandomDataForObject.generateRandomValues(new ClientEntity());

        when(travelService.getById(post.getTravelId())).thenReturn(travel);
        when(clientService.getById(post.getClientId())).thenReturn(client);
        ticket.setStatus(TicketStatus.RESERVED);
        when(ticketRepository.save(any())).thenReturn(ticket);

        TicketModel result = ticketService.create(post);

        assertEquals(TicketStatus.RESERVED, result.getStatus());
        assertEquals(ticket.getClazz() ,result.getClazz());
        assertEquals(ticket.getId(), result.getId());
        assertEquals(ticket.getPrice(), result.getPrice());
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void createTicketTest_Failure() throws IllegalAccessException {
        TicketDTOPost post = RandomDataForObject.generateRandomValues(new TicketDTOPost());
        TravelEntity travel = RandomDataForObject.generateRandomValues(new TravelEntity());

        travel.getPlane().setCapacity(new BigInteger("-1"));

        when(travelService.getById(post.getTravelId())).thenReturn(travel);
        when(ticketRepository.save(any())).thenReturn(ticket);

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> ticketService.create(post));

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    void payTicketTest() throws IllegalAccessException {
        ticket.setStatus(TicketStatus.RESERVED);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any())).thenReturn(ticket);

        TicketModel result = ticketService.payTicket(1L);

        assertEquals(TicketStatus.PAID, result.getStatus());
        assertEquals(ticket.getClazz() ,result.getClazz());
        assertEquals(ticket.getId(), result.getId());
        assertEquals(ticket.getPrice(), result.getPrice());
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void payTicketTest_FailureNotFound() throws IllegalAccessException {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> ticketService.payTicket(1L));

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void payTicketTest_FailureBadRequest() throws IllegalAccessException {
        ticket.setStatus(TicketStatus.PAID);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> ticketService.payTicket(1L));

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void cancelTicketTest() throws IllegalAccessException {
        ticket.setStatus(TicketStatus.RESERVED);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any())).thenReturn(ticket);

        TicketModel result = ticketService.cancelTicket(1L);

        assertEquals(TicketStatus.CANCELLED, result.getStatus());
        assertFalse(ticket.getIsActive());
        assertEquals(ticket.getClazz() ,result.getClazz());
        assertEquals(ticket.getId(), result.getId());
        assertEquals(ticket.getPrice(), result.getPrice());
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void cancelTicketTest_FailureNotFound() throws IllegalAccessException {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> ticketService.cancelTicket(1L));

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void cancelTicketTest_FailureBadRequest() throws IllegalAccessException {
        ticket.setStatus(TicketStatus.PAID);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> ticketService.cancelTicket(1L));

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void refundTicketTest() throws IllegalAccessException {
        ticket.setStatus(TicketStatus.PAID);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any())).thenReturn(ticket);

        TicketModel result = ticketService.refundTicket(1L);

        assertEquals(TicketStatus.REFUND, result.getStatus());
        assertFalse(ticket.getIsActive());
        assertEquals(ticket.getClazz() ,result.getClazz());
        assertEquals(ticket.getId(), result.getId());
        assertEquals(ticket.getPrice(), result.getPrice());
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void refundlTicketTest_FailureBadRequest() throws IllegalAccessException {
        ticket.setStatus(TicketStatus.RESERVED);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> ticketService.refundTicket(1L));

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
