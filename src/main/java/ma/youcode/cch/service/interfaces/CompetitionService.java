package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.DTOs.competition.CompetitionResponseDTO;
import ma.youcode.cch.DTOs.competition.CreateCompetitionDTO;
import ma.youcode.cch.entity.Competition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionService {
    CompetitionResponseDTO createCompetition(CreateCompetitionDTO competitionDTO);
    CompetitionResponseDTO updateCompetition(UUID competitionId , CreateCompetitionDTO competitionDTO);
    CompetitionResponseDTO deleteCompetition(UUID competitionId);
    List<CompetitionResponseDTO> getAllCompetitions();
    List<CompetitionResponseDTO>getFilteredCompetitions(String place , LocalDate startDate);
    CompetitionResponseDTO getCompetition(UUID competitionId);
    Optional<Competition> getCompetitionById(UUID competitionId);

}
