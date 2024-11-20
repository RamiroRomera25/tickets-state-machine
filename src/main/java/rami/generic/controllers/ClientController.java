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
import rami.generic.dtos.clients.ClientDTOPost;
import rami.generic.entities.ClientEntity;
import rami.generic.models.ClientModel;
import rami.generic.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    private ClientService<ClientEntity, Long, ClientModel, ClientDTOPost> clientService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getModelById(id));
    }

    @PostMapping("")
    public ResponseEntity<ClientModel> create(@RequestBody @Valid ClientDTOPost dtoPost) {
        return ResponseEntity.ok(clientService.create(dtoPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientModel> delete(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.delete(id));
    }

    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<ClientModel> reactivate(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.reactivate(id));
    }
}
