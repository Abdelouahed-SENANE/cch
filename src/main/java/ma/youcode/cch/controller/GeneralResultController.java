package ma.youcode.cch.controller;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.dtos.generalResult.CreateGeneralResultDTO;
import ma.youcode.cch.dtos.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.service.interfaces.GeneralResultService;
import org.springframework.http.HttpStatus;
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

//    @GetMapping
//    public ResponseEntity<List<GeneralResultResponseDTO>> generalResults() {
//        List<GeneralResultResponseDTO> getGeneralResultsDTOs = generalResultService.getAllGeneralResults();
//        return ResponseEntity.ok(getGeneralResultsDTOs);
//    }
//
//    @GetMapping("{generalResultId}")
//    public ResponseEntity<GeneralResultResponseDTO> generalResult(@PathVariable UUID generalResultId) {
//        return ResponseEntity.ok(generalResultService.getGeneralResult(generalResultId));
//    }

    @PostMapping
    public ResponseEntity<GeneralResultResponseDTO> addGeneralResult(@RequestBody CreateGeneralResultDTO createGeneralResultDTO) {
        return ResponseEntity.ok(generalResultService.createGeneralResult(createGeneralResultDTO));
    }

//    @PutMapping("{generalResultId}")
//    public ResponseEntity<?> updateGeneralResult(@PathVariable UUID generalResultId, @RequestBody CreateGeneralResultDTO createGeneralResultDTO) {
//
//        try {
//            return ResponseEntity.ok(generalResultService.updateGeneralResult(generalResultId, createGeneralResultDTO));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//
//    }

//    @DeleteMapping("{generalResultId}")
//    public ResponseEntity<GeneralResultResponseDTO> deleteGeneralResult(@PathVariable UUID generalResultId) {
//        return ResponseEntity.ok(generalResultService.deleteGeneralResult(generalResultId));
//    }

//    @GetMapping("/filter")
//    public ResponseEntity<?> sortedGeneralResults(@RequestParam(required = false) String place , @RequestParam(required = false) LocalDate startDate) {
//        return ResponseEntity.ok(generalResultService.getFilteredGeneralResults(place , startDate));
//    }


}
