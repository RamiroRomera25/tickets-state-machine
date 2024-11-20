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
import rami.generic.services.PlaneService;

@RestController
@RequestMapping("/clients")
public class PlaneController {
    
    @Autowired
    private PlaneService<ClientEntity, Long, ClientModel, ClientDTOPost> planeService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(planeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(planeService.getModelById(id));
    }

    @PostMapping("")
    public ResponseEntity<ClientModel> create(@RequestBody @Valid ClientDTOPost dtoPost) {
        return ResponseEntity.ok(planeService.create(dtoPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientModel> delete(@PathVariable Long id) {
        return ResponseEntity.ok(planeService.delete(id));
    }

    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<ClientModel> reactivate(@PathVariable Long id) {
        return ResponseEntity.ok(planeService.reactivate(id));
    }
}
