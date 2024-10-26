package ma.youcode.cch.controller;

import ma.youcode.cch.DTOs.stageResult.CreateStageResultDTO;
import ma.youcode.cch.DTOs.stageResult.StageResultResponseDTO;
import ma.youcode.cch.service.interfaces.StageResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stage-results")
public class StageResultController {

    private final StageResultService stageResultService;

    public StageResultController(StageResultService stageResultService) {
        this.stageResultService = stageResultService;
    }

    @GetMapping
    public ResponseEntity<List<StageResultResponseDTO>> stageResults() {
        List<StageResultResponseDTO> getStageResultsDTOs = stageResultService.getAllStageResults();
        return ResponseEntity.ok(getStageResultsDTOs);
    }

    @GetMapping("{cyclistId}/{stageId}")
    public ResponseEntity<StageResultResponseDTO> stageResult(@PathVariable UUID cyclistId , @PathVariable UUID competitionId) {
        return ResponseEntity.ok(stageResultService.getStageResult(cyclistId , competitionId));
    }

    @PostMapping
    public ResponseEntity<StageResultResponseDTO> addStageResult(@RequestBody CreateStageResultDTO createStageResultDTO) {
        return ResponseEntity.ok(stageResultService.createStageResult(createStageResultDTO));
    }

    @PutMapping("{cyclistId}/{stageId}")
    public ResponseEntity<StageResultResponseDTO> updateStageResult(@PathVariable UUID cyclistId, @PathVariable UUID stageId , @RequestBody CreateStageResultDTO createStageResultDTO) {
            return ResponseEntity.ok(stageResultService.updateStageResult( createStageResultDTO,cyclistId,stageId));

    }

    @DeleteMapping("{cyclistId}/{stageId}")
    public ResponseEntity<StageResultResponseDTO> deleteStageResult(@PathVariable UUID cyclistId , @PathVariable UUID stageId) {
        return ResponseEntity.ok(stageResultService.deleteStageResult(cyclistId, stageId));
    }




}
