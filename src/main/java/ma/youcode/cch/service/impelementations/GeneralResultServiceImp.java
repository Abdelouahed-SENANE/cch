package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.repository.interfaces.GeneralResultDao;
import ma.youcode.cch.DTOs.stageResult.CreateGeneralResultDTO;
import ma.youcode.cch.DTOs.stageResult.GeneralResultResponseDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.mapper.GeneralResultMapper;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.GeneralResultService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeneralResultServiceImp implements GeneralResultService {

    private final GeneralResultDao generalResultDao;
    private final CyclistService cyclistService;
    private final CompetitionService competitionService;
    private final GeneralResultMapper generalResultMapper;


    public GeneralResultServiceImp(GeneralResultDao generalResultDao, CyclistService cyclistDao, CompetitionService competitionDao, @Lazy GeneralResultMapper generalResultMapper) {
        this.generalResultDao = generalResultDao;
        this.competitionService = competitionDao;
        this.cyclistService = cyclistDao;
        this.generalResultMapper = generalResultMapper;
    }

    @Override
    public GeneralResultResponseDTO createGeneralResult(CreateGeneralResultDTO generalResultDTO) {

        if (!isCyclistAndCompetitionValid(generalResultDTO.getCyclistId(), generalResultDTO.getCompetitionId())) {
            throw new EntityNotFoundException("Validation failed for cyclist and competition.");
        }

        GeneralResult generalResult = generalResultMapper.toGeneralResultEntity(generalResultDTO);

        Optional<GeneralResult> getGeneralResult = generalResultDao.findById(generalResult.getGeneralResultId());

        if (getGeneralResult.isPresent()) {
            throw new IllegalArgumentException("This " + getGeneralResult.get().getCyclist().getLastName() + " Already  Subscribed On This Competition " + getGeneralResult.get().getCompetition().getCompetitionName());
        }

        return generalResultMapper.toResponseDTO(generalResultDao.save(generalResult));
    }


    private boolean isCyclistAndCompetitionValid(UUID cyclistId, UUID competitionId) {

        Optional<Cyclist> cyclist = cyclistService.getCyclistById(cyclistId);
        Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
        return cyclist.isPresent() && competition.isPresent();
    }


    @Override
    public GeneralResultResponseDTO updateGeneralResult(UUID cyclistId , UUID competitionId,CreateGeneralResultDTO generalResultDTO) {

        GeneralResultId generalResultId = this.getGeneralResultId(cyclistId , competitionId);

        if (!isGeneralResult(generalResultId)) {
            throw new EntityNotFoundException("General Result Not Found");
        }

        GeneralResult generalResult = generalResultMapper.toGeneralResultEntity(generalResultDTO);
        generalResult.setGeneralResultId(generalResultId);

        return generalResultMapper.toResponseDTO(generalResultDao.update(generalResult));
    }

    private boolean isGeneralResult(GeneralResultId generalResultId) {
        return generalResultDao.findById(generalResultId).isPresent();
    }

    @Override
    public GeneralResultResponseDTO deleteGeneralResult(UUID cyclistId , UUID competitionId) {

        GeneralResultId generalResultId = this.getGeneralResultId(cyclistId , competitionId);

        if (!isGeneralResult(generalResultId)) {
            throw new EntityNotFoundException("General Result Not Found");
        }

        GeneralResult generalResult = generalResultDao.findById(generalResultId).orElse(null);
        return generalResultMapper.toResponseDTO(generalResultDao.delete(generalResult));
    }

    @Override
    public List<GeneralResultResponseDTO> getAllGeneralResults() {
        return this.convertToListResponseDTO(generalResultDao.findAll());
    }

    private List<GeneralResultResponseDTO> convertToListResponseDTO(Set<GeneralResult> generalResults) {
        return generalResults.stream().map(generalResultMapper::toResponseDTO).collect(Collectors.toList());
    }


    private GeneralResultId getGeneralResultId (UUID cyclistId , UUID competitionId) {
        return new GeneralResultId(cyclistId , competitionId);
    }

    @Override
    public GeneralResultResponseDTO getGeneralResult(UUID cyclistId , UUID competitionId) {
        GeneralResultId generalResultId = this.getGeneralResultId(cyclistId , competitionId);
        return generalResultMapper.toResponseDTO(generalResultDao.findById(generalResultId).orElse(null));
    }
}
