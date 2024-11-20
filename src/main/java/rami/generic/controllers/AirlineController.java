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
import rami.generic.dtos.airline.AirlineDTOPost;
import rami.generic.dtos.clients.ClientDTOPost;
import rami.generic.entities.AirlineEntity;
import rami.generic.entities.ClientEntity;
import rami.generic.models.AirlineModel;
import rami.generic.models.ClientModel;
import rami.generic.services.AirlineService;

@RestController
@RequestMapping("/airlines")
public class AirlineController {
    
    @Autowired
    private AirlineService<AirlineEntity, Long, AirlineModel, AirlineDTOPost> airlineService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(airlineService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.getModelById(id));
    }

    @PostMapping("")
    public ResponseEntity<AirlineModel> create(@RequestBody @Valid AirlineDTOPost dtoPost) {
        return ResponseEntity.ok(airlineService.create(dtoPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AirlineModel> delete(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.delete(id));
    }

    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<AirlineModel> reactivate(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.reactivate(id));
    }
}
