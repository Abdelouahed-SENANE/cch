package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.daos.interfaces.*;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.Result;
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
    public Result createResult(Result result) {

        Stage stage = getStage(result.getResultId().getStageId());
        Cyclist cyclist = getCyclist(result.getResultId().getCyclistId());

        if (!isCyclistSubscribedInCompetition(cyclist , stage)) {
             throw new EntityNotFoundException("The cyclist cannot added on this stage because is not register in this competition " + stage.getCompetition().getCompetitionName());
        }

//        result.setResultId(result.getResultId());
        result.setStage(stage);
        result.setCyclist(cyclist);
        return resultDao.save(result);
    }

    @Override
    public Result updateResult(Result result) {

        Optional<Result> optionalResult = resultDao.findById(result.getResultId());

        if (optionalResult.isPresent()) {
            return resultDao.update(result);
        }

        return null;
    }

    @Override
    public Result deleteResult(Result result) {
        return resultDao.delete(result);
    }

    @Override
    public Set<Result> getAllResults() {
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
