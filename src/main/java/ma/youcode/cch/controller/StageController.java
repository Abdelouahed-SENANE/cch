package ma.youcode.cch.controller;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.stage.StageResponseDTO;
import ma.youcode.cch.DTOs.stage.CreateStageDTO;
import ma.youcode.cch.service.interfaces.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stages")
public class StageController {

    private final StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping
    public ResponseEntity<List<StageResponseDTO>> stages() {
        List<StageResponseDTO> getStagesDTOs = stageService.getAllStages();
        return ResponseEntity.ok(getStagesDTOs);
    }

    @GetMapping("{stageId}")
    public ResponseEntity<StageResponseDTO> stage(@PathVariable UUID stageId) {
        return ResponseEntity.ok(stageService.getStage(stageId));
    }

    @PostMapping
    public ResponseEntity<StageResponseDTO> addStage(@RequestBody CreateStageDTO createStageDTO) {
        return ResponseEntity.ok(stageService.createStage(createStageDTO));
    }

    @PutMapping("{stageId}")
    public ResponseEntity<?> updateStage(@PathVariable UUID stageId, @RequestBody CreateStageDTO createStageDTO) {

        try {
            return ResponseEntity.ok(stageService.updateStage(stageId, createStageDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("{stageId}")
    public ResponseEntity<StageResponseDTO> deleteStage(@PathVariable UUID stageId) {
        return ResponseEntity.ok(stageService.deleteStage(stageId));
    }

//    @GetMapping("/filter")
//    public ResponseEntity<?> sortedStages(@RequestParam(required = false) String place , @RequestParam(required = false) LocalDate startDate) {
//        return ResponseEntity.ok(stageService.getFilteredStages(place , startDate));
//    }


}
