package ma.youcode.cch.controller;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.competition.CreateCompetitionDTO;
import ma.youcode.cch.DTOs.competition.CompetitionResponseDTO;
import ma.youcode.cch.service.interfaces.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDTO>> competitions() {
        List<CompetitionResponseDTO> getCompetitionsDTOs = competitionService.getAllCompetitions();
        return ResponseEntity.ok(getCompetitionsDTOs);
    }

    @GetMapping("{competitionId}")
    public ResponseEntity<CompetitionResponseDTO> competition(@PathVariable UUID competitionId) {
        return ResponseEntity.ok(competitionService.getCompetition(competitionId));
    }

    @PostMapping
    public ResponseEntity<CompetitionResponseDTO> addCompetition(@RequestBody CreateCompetitionDTO createCompetitionDTO) {
        return ResponseEntity.ok(competitionService.createCompetition(createCompetitionDTO));
    }

    @PutMapping("{competitionId}")
    public ResponseEntity<?> updateCompetition(@PathVariable UUID competitionId, @RequestBody CreateCompetitionDTO createCompetitionDTO) {

        try {
            return ResponseEntity.ok(competitionService.updateCompetition(competitionId, createCompetitionDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("{competitionId}")
    public ResponseEntity<CompetitionResponseDTO> deleteCompetition(@PathVariable UUID competitionId) {
        return ResponseEntity.ok(competitionService.deleteCompetition(competitionId));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> sortedCompetitions(@RequestParam(required = false) String place , @RequestParam(required = false) LocalDate startDate) {
        return ResponseEntity.ok(competitionService.getFilteredCompetitions(place , startDate));
    }


}
