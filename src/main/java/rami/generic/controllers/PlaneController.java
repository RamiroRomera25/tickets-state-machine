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
import rami.generic.dtos.plane.PlaneDTOPost;
import rami.generic.entities.PlaneEntity;
import rami.generic.models.PlaneModel;
import rami.generic.services.PlaneService;

@RestController
@RequestMapping("/planes")
public class PlaneController {
    
    @Autowired
    private PlaneService<PlaneEntity, Long, PlaneModel, PlaneDTOPost> planeService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(planeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaneModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(planeService.getModelById(id));
    }

    @PostMapping("")
    public ResponseEntity<PlaneModel> create(@RequestBody @Valid PlaneDTOPost dtoPost) {
        return ResponseEntity.ok(planeService.create(dtoPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlaneModel> delete(@PathVariable Long id) {
        return ResponseEntity.ok(planeService.delete(id));
    }

    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<PlaneModel> reactivate(@PathVariable Long id) {
        return ResponseEntity.ok(planeService.reactivate(id));
    }
}
