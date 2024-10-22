package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.daos.interfaces.*;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.StageResult;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ResultServiceImp implements ResultService {

    private final ResultDao resultDao;
    private final GeneralResultDao generalResultDao;
    private final StageDao stageDao;
    private final CyclistDao cyclistDao;
//    private final CompetitionDao competitionDao;


    public ResultServiceImp(ResultDao resultDao, CyclistDao cyclistDao, GeneralResultDao generalResultDao, StageDao stageDao, CompetitionDao competitionDao) {
        this.resultDao = resultDao;
        this.generalResultDao = generalResultDao;
        this.cyclistDao = cyclistDao;
        this.stageDao = stageDao;
//        this.competitionDao = competitionDao;
    }

    @Override
    public StageResult createResult(StageResult stageResult) {

        Stage stage = getStage(stageResult.getResultId().getStageId());
        Cyclist cyclist = getCyclist(stageResult.getResultId().getCyclistId());

        if (!isCyclistSubscribedInCompetition(cyclist , stage)) {
             throw new EntityNotFoundException("The cyclist cannot added on this stage because is not register in this competition " + stage.getCompetition().getCompetitionName());
        }

//        stageResult.setResultId(stageResult.getResultId());
        stageResult.setStage(stage);
        stageResult.setCyclist(cyclist);
        return resultDao.save(stageResult);
    }

    @Override
    public StageResult updateResult(StageResult stageResult) {

        Optional<StageResult> optionalResult = resultDao.findById(stageResult.getResultId());

        if (optionalResult.isPresent()) {
            return resultDao.update(stageResult);
        }

        return null;
    }

    @Override
    public StageResult deleteResult(StageResult stageResult) {
        return resultDao.delete(stageResult);
    }

    @Override
    public Set<StageResult> getAllResults() {
        return resultDao.findAll();
    }

    private boolean isCyclistSubscribedInCompetition(Cyclist cyclist , Stage stage){

        GeneralResultId generalResultId = new GeneralResultId();
        generalResultId.setCompetitionId(stage.getCompetition().getCompetitionId());
        generalResultId.setCyclistId(cyclist.getCyclistId());
        Optional<GeneralResult> generalResult = generalResultDao.findById(generalResultId);

        return generalResult.isPresent();
    }

    private Stage getStage(UUID stageId) {
        return stageDao.findById(stageId).orElseThrow(() -> new EntityNotFoundException("Stage is not found"));
    }
    private Cyclist getCyclist(UUID cyclistId) {
        return cyclistDao.findById(cyclistId).orElseThrow(() -> new EntityNotFoundException("Cyclist is not found"));
    }





}
