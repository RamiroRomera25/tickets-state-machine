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
import rami.generic.dtos.travel.TravelDTOPost;
import rami.generic.entities.TravelEntity;
import rami.generic.models.TravelModel;
import rami.generic.services.TravelService;

@RestController
@RequestMapping("/travels")
public class TravelController {
    
    @Autowired
    private TravelService<TravelEntity, Long, TravelModel, TravelDTOPost> travelService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(travelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(travelService.getModelById(id));
    }

    @PostMapping("")
    public ResponseEntity<TravelModel> create(@RequestBody @Valid TravelDTOPost dtoPost) {
        return ResponseEntity.ok(travelService.create(dtoPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TravelModel> delete(@PathVariable Long id) {
        return ResponseEntity.ok(travelService.delete(id));
    }
}
