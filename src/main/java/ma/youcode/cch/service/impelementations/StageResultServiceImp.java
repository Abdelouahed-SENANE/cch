package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.competition.CompetitionResponseDTO;
import ma.youcode.cch.DTOs.cyclist.CyclistResponseDTO;
import ma.youcode.cch.DTOs.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.DTOs.stage.StageResponseDTO;
import ma.youcode.cch.DTOs.stageResult.CreateStageResultDTO;
import ma.youcode.cch.DTOs.stageResult.StageResultResponseDTO;
import ma.youcode.cch.entity.embedded.StageResultId;
import ma.youcode.cch.mapper.StageResultMapper;
import ma.youcode.cch.repository.interfaces.*;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.StageResult;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.service.interfaces.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StageResultServiceImp implements StageResultService {

    private final StageResultDao stageResultDao;
    private final GeneralResultService generalResultService;
    private final StageService stageService;
    private final CyclistService cyclistService;
    private final StageResultMapper stageResultMapper;


    public StageResultServiceImp(StageResultDao stageResultDao, StageResultMapper stageResultMapper, CyclistService cyclistService, GeneralResultService generalResultService, StageService stageService) {
        this.stageResultDao = stageResultDao;
        this.generalResultService = generalResultService;
        this.cyclistService = cyclistService;
        this.stageService = stageService;
        this.stageResultMapper = stageResultMapper;


    }

    @Override
    public StageResultResponseDTO createStageResult(CreateStageResultDTO stageResultDTO) {

        StageResult toStageResult = stageResultMapper.toStageResultEntity(stageResultDTO);

        if (isStageResult(toStageResult.getStageResultId())) {
            throw new EntityExistsException("The stage result Already Exists");
        }

        Optional<Stage> optionalStage = getStage(toStageResult.getStage().getStageId());
        Optional<Cyclist> optionalCyclist = getCyclist(toStageResult.getStageResultId().getCyclistId());

        if (!optionalStage.isPresent() || !optionalCyclist.isPresent()) {
            throw new EntityNotFoundException("Stage or Cyclist Not Found");
        }
        Cyclist cyclist = optionalCyclist.get();
        Stage stage = optionalStage.get();

        if (!isCyclistSubscribedInCompetition(cyclist.getCyclistId(), stage.getCompetition().getCompetitionId())) {
            throw new IllegalArgumentException("The cyclist cannot added on this stage because is not subscribed on this competition " + stage.getCompetition().getCompetitionName());
        }

        return stageResultMapper.toResponseDTO(stageResultDao.save(toStageResult));
    }

    @Override
    public StageResultResponseDTO updateStageResult(CreateStageResultDTO stageResultDTO, UUID cyclistId, UUID stageId) {
        StageResultId stageResultId = this.getStageResultId(cyclistId, stageId);

        StageResult stageResult = stageResultDao.findById(stageResultId).orElseThrow(() -> new EntityNotFoundException("Result Stage Not Found"));

        if (stageResult.getStage().isCompleted()) {
            throw new IllegalArgumentException("Cannot update this Stage result because is Completed");
        }
        StageResult toStageResult = stageResultMapper.toStageResultEntity(stageResultDTO);

        return stageResultMapper.toResponseDTO(stageResultDao.update(toStageResult));
    }

    @Override
    public StageResultResponseDTO deleteStageResult(UUID cyclistId, UUID stageId) {

        StageResultId stageResultId = this.getStageResultId(cyclistId, stageId);
        Optional<StageResult> stageResultOptional = stageResultDao.findById(stageResultId);

        if (!stageResultOptional.isPresent()) {
            throw new EntityNotFoundException("Result Stage Not Found");
        }
        StageResult stageResult = stageResultOptional.get();

        return stageResultMapper.toResponseDTO(stageResultDao.delete(stageResult));
    }

    @Override
    public List<StageResultResponseDTO> getAllStageResults() {
        return this.convertToListResponseDTO(stageResultDao.findAll());
    }

    private List<StageResultResponseDTO> convertToListResponseDTO(Set<StageResult> stageResults) {
        return stageResults.stream().map(stageResultMapper::toResponseDTO).collect(Collectors.toList());
    }

    public boolean isCyclistSubscribedInCompetition(UUID cyclistId, UUID competitionId) {
        GeneralResultResponseDTO generalResult = generalResultService.getGeneralResult(cyclistId, competitionId);
        return generalResult != null;
    }

    public boolean isStageResult(StageResultId stageResultId) {
        Optional<StageResult> stageResult = stageResultDao.findById(stageResultId);
        return stageResult.isPresent();
    }

    public StageResultId getStageResultId(UUID cyclistId, UUID stageId) {
        return new StageResultId(cyclistId, stageId);
    }

    public Optional<Stage> getStage(UUID stageId) {
        return stageService.getStageEntity(stageId);
    }

    public Optional<Cyclist> getCyclist(UUID cyclistId) {
        return cyclistService.getCyclistById(cyclistId);
    }

    @Override
    public StageResultResponseDTO getStageResult(UUID cyclistId, UUID stageId) {
        StageResultId stageResultId = this.getStageResultId(cyclistId, stageId);
        return stageResultMapper.toResponseDTO(stageResultDao.findById(stageResultId).orElseThrow(() -> new EntityNotFoundException("Result Stage Not Found")));
    }


}
