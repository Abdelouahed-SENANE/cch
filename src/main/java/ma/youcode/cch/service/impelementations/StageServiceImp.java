package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.repository.interfaces.StageDao;
import ma.youcode.cch.DTOs.stage.CreateStageDTO;
import ma.youcode.cch.DTOs.stage.StageResponseDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.StageResult;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.mapper.StageMapper;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.StageService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StageServiceImp implements StageService {

    private final StageDao stageDao;
    private final CompetitionService competitionService;
    private final StageMapper stageMapper;

    public StageServiceImp(StageDao stageDao, CompetitionService competitionService, StageMapper stageMapper) {
        this.stageDao = stageDao;
        this.competitionService = competitionService;
        this.stageMapper = stageMapper;

    }

    @Override
    public StageResponseDTO createStage(CreateStageDTO stageDTO) {

        Optional<Competition> optionalCompetition = competitionService.getCompetitionById(stageDTO.getCompetitionId());

        if (!optionalCompetition.isPresent()) {
            throw new EntityNotFoundException("Competition Not Found");
        }

        Competition competition = optionalCompetition.get();

        if (competition.getStages().size() >= competition.getNumberOfStage()) {
            throw new IllegalArgumentException("Cannot add new stage because you have reached the maximum number of stages " + competition.getNumberOfStage());
        }
        Stage stage = stageMapper.toStageEntity(stageDTO);
        stage.setStageNumber(calculateNextStageNumber(competition.getStages()));

        return stageMapper.toResponseDTO(stageDao.save(stage));
    }

    @Override
    public StageResponseDTO updateStage(UUID stageId ,CreateStageDTO stageDTO) {
        Optional<Stage> stageOptional = stageDao.findById(stageId);

        if (!stageOptional.isPresent()) {
            throw new EntityNotFoundException("Stage Not Found");
        }

        Stage updated = stageMapper.toStageEntity(stageDTO);
        updated.setStageId(stageId);
        updated.setStageNumber(stageOptional.get().getStageNumber());

        return stageMapper.toResponseDTO(stageDao.update(updated));
    }

    @Override
    public StageResponseDTO deleteStage(UUID stageId) {
        Optional<Stage> stageOptional = stageDao.findById(stageId);

        if (!stageOptional.isPresent()) {
            throw new EntityNotFoundException("Stage Not Found");
        }

        Stage deleted = stageOptional.orElse(null);

        return stageMapper.toResponseDTO(stageDao.delete(deleted));

    }

    @Override
    public List<StageResponseDTO> getAllStages() {
        return this.convertToListStageResponseDTO(stageDao.findAll());
    }

    private int calculateNextStageNumber(Set<Stage> stages) {
        return  stages.stream()
                .mapToInt(Stage::getStageNumber)
                .max()
                .orElse(0) + 1;
    }


    private List<StageResponseDTO> convertToListStageResponseDTO(Set<Stage> stages) {
        return stages.stream()
                .map(stageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StageResponseDTO getStage(UUID id) {
        Stage getStage = stageDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Stage Not Found"));
        return stageMapper.toResponseDTO(getStage);
    }

//    public Optional<Stage> getStageById(UUID id) {
//        return stageDao.findById(id);
//    }

    @Override
    public Optional<Stage> getStageWithResultOrderedByDuration(UUID id) {
        Optional<Stage> stage = stageDao.findById(id);
        if (stage.isPresent()) {
            Stage getStage = stage.get();
            List<StageResult> orderedStageResult = getStage.getStageResults().stream()
                    .sorted(Comparator.comparing(StageResult::getStageDuration))
                    .collect(Collectors.toList());

            getStage.setStageResults(orderedStageResult);
            return Optional.of(getStage);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Stage> getStageEntity(UUID stageId) {
        return stageDao.findById(stageId);
    }

    @Override
    public StageResponseDTO patchCompleted(CreateStageDTO createStageDTO, UUID stageId) {
        Stage updated = this.getStageEntity(stageId).orElseThrow(() ->new EntityNotFoundException("Stage Not Found"));
        updated.setCompleted(createStageDTO.isCompleted());
        return stageMapper.toResponseDTO(stageDao.updateIsCompleted(updated));
    }
}
