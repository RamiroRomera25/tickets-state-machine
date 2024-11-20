package rami.generic.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rami.generic.dtos.ticket.TicketDTOPost;
import rami.generic.entities.TicketEntity;
import rami.generic.models.TicketModel;
import rami.generic.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    @Autowired
    private TicketService<TicketEntity, Long, TicketModel, TicketDTOPost> ticketService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getModelById(id));
    }

    @PostMapping("")
    public ResponseEntity<TicketModel> create(@RequestBody @Valid TicketDTOPost dtoPost) {
        return ResponseEntity.ok(ticketService.create(dtoPost));
    }

    @PostMapping("/pay/{ticketId}")
    public ResponseEntity<TicketModel> pay(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.payTicket(ticketId));
    }

    @PostMapping("/refund/{ticketId}")
    public ResponseEntity<TicketModel> refund(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.refundTicket(ticketId));
    }

    @PostMapping("/cancel/{ticketId}")
    public ResponseEntity<TicketModel> cancel(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.cancelTicket(ticketId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TicketModel> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.cancelTicket(id));
    }

    @PostMapping("/completed/{ticketId}")
    public ResponseEntity<TicketModel> completed(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.completeTicket(ticketId));
    }
}
