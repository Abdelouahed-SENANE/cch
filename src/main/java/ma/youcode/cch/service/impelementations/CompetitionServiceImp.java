package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.daos.interfaces.CompetitionDao;
import ma.youcode.cch.dtos.competition.CompetitionResponseDTO;
import ma.youcode.cch.dtos.competition.CreateCompetitionDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.mapper.CompetitionMapper;
import ma.youcode.cch.service.interfaces.CompetitionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CompetitionServiceImp implements CompetitionService {

    private final CompetitionDao competitionDao;
    private final CompetitionMapper competitionMapper;

    public CompetitionServiceImp(CompetitionDao competitionDao ,CompetitionMapper competitionMapper){
        this.competitionDao = competitionDao;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public CompetitionResponseDTO createCompetition(CreateCompetitionDTO createCompetitionDTO) {

        Competition saved = competitionMapper.toCompetitionEntity(createCompetitionDTO);
        saved = competitionDao.save(saved);

        return competitionMapper.toResponseDTO(saved);
    }

    @Override
    public CompetitionResponseDTO updateCompetition( UUID competitionId,CreateCompetitionDTO competitionDTO) {

        if (getCompetitionById(competitionId).isPresent()) {
            throw new EntityNotFoundException("Competition Not Found");
        }
        Competition updated = competitionMapper.toCompetitionEntity(competitionDTO);
        updated.setCompetitionId(competitionId);

        return competitionMapper.toResponseDTO(competitionDao.update(updated));
    }

    @Override
    public CompetitionResponseDTO deleteCompetition(UUID competitionId) {

        if (getCompetitionById(competitionId).isPresent()) {
            throw new EntityNotFoundException("Competition Not Found");
        }
        Competition deleted = getCompetitionById(competitionId).orElse(null);

        return competitionMapper.toResponseDTO(competitionDao.delete(deleted));
    }

    @Override
    public List<CompetitionResponseDTO> getAllCompetitions() {
        return this.convertToCompetitionResponseDTO(competitionDao.findAll());
    }

    @Override
    public List<CompetitionResponseDTO> getFilteredCompetitions(String place, LocalDate startDate) {
        return this.convertToCompetitionResponseDTO(competitionDao.findFilteredCompetitions(place , startDate));
    }

    @Override
    public CompetitionResponseDTO getCompetition(UUID id) {
        Competition getCompetition = competitionDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Competition Not Found"));
        return competitionMapper.toResponseDTO(getCompetition);
    }

    private Optional<Competition> getCompetitionById(UUID competitionId) {
        return competitionDao.findById(competitionId);
    }

    private List<CompetitionResponseDTO> convertToCompetitionResponseDTO(Set<Competition> competitions) {
        return  competitions.stream()
                .map(competitionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


}
