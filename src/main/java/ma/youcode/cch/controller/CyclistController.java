package ma.youcode.cch.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import ma.youcode.cch.DTOs.cyclist.CreateCyclistDTO;
import ma.youcode.cch.DTOs.cyclist.CyclistResponseDTO;
import ma.youcode.cch.service.interfaces.CyclistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cyclists")
public class CyclistController {

    private final CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @GetMapping
    public ResponseEntity<List<CyclistResponseDTO>> cyclists() {

        List<CyclistResponseDTO> getCyclistsDTOs = cyclistService.getAllCyclists();
        return ResponseEntity.ok(getCyclistsDTOs);
    }

    @GetMapping("{cyclistId}")
    public ResponseEntity<CyclistResponseDTO> cyclist(@PathVariable UUID cyclistId) {

        return ResponseEntity.ok(cyclistService.getCyclist(cyclistId));
    }

    @PostMapping
    public ResponseEntity<CyclistResponseDTO> addCyclist(@Valid @RequestBody CreateCyclistDTO createCyclistDTO) {
        return ResponseEntity.ok(cyclistService.createCyclist(createCyclistDTO));
    }

    @PutMapping("{cyclistId}")
    public ResponseEntity<?> updateCyclist(@PathVariable UUID cyclistId, @RequestBody CreateCyclistDTO createCyclistDTO) {

        try {
            return ResponseEntity.ok(cyclistService.updateCyclist(cyclistId, createCyclistDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("{cyclistId}")
    public ResponseEntity<CyclistResponseDTO> deleteCyclist(@PathVariable UUID cyclistId) {
        return ResponseEntity.ok(cyclistService.deleteCyclist(cyclistId));
    }

    @GetMapping("/sort")
    public ResponseEntity<?> sortedCyclists(@RequestParam String criteria) {
        return ResponseEntity.ok(cyclistService.getSortedCyclists(criteria));
    }


}
