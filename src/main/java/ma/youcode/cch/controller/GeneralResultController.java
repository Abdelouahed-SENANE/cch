package ma.youcode.cch.controller;

import ma.youcode.cch.DTOs.generalResult.CreateGeneralResultDTO;
import ma.youcode.cch.DTOs.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.service.interfaces.GeneralResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/general-results")
public class GeneralResultController {

    private final GeneralResultService generalResultService;

    public GeneralResultController(GeneralResultService generalResultService) {
        this.generalResultService = generalResultService;
    }

    @GetMapping
    public ResponseEntity<List<GeneralResultResponseDTO>> generalResults() {
        List<GeneralResultResponseDTO> getGeneralResultsDTOs = generalResultService.getAllGeneralResults();
        return ResponseEntity.ok(getGeneralResultsDTOs);
    }

    @GetMapping("{cyclistId}/{competitionId}")
    public ResponseEntity<GeneralResultResponseDTO> generalResult(@PathVariable UUID cyclistId , @PathVariable UUID competitionId) {
        return ResponseEntity.ok(generalResultService.getGeneralResult(cyclistId , competitionId));
    }

    @PostMapping
    public ResponseEntity<GeneralResultResponseDTO> addGeneralResult(@RequestBody CreateGeneralResultDTO createGeneralResultDTO) {
        return ResponseEntity.ok(generalResultService.createGeneralResult(createGeneralResultDTO));
    }

    @PutMapping("{cyclistId}/{competitionId}")
    public ResponseEntity<GeneralResultResponseDTO> updateGeneralResult(@PathVariable UUID cyclistId, @PathVariable UUID competitionId , @RequestBody CreateGeneralResultDTO createGeneralResultDTO) {
            return ResponseEntity.ok(generalResultService.updateGeneralResult(cyclistId,competitionId,createGeneralResultDTO));

    }

    @DeleteMapping("{cyclistId}/{competitionId}")
    public ResponseEntity<GeneralResultResponseDTO> deleteGeneralResult(@PathVariable UUID cyclistId , @PathVariable UUID competitionId) {
        return ResponseEntity.ok(generalResultService.deleteGeneralResult(cyclistId, competitionId));
    }




}
