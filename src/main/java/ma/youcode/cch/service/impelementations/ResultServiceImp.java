package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.daos.interfaces.*;
import ma.youcode.cch.entity.*;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.entity.embedded.ResultId;
import ma.youcode.cch.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ResultServiceImp implements ResultService {

    private final ResultDao resultDao;
    private final GeneralResultDao generalResultDao;
    private final StageDao stageDao;
    private final CyclistDao cyclistDao;
    private final CompetitionDao competitionDao;


    public ResultServiceImp(ResultDao resultDao, CyclistDao cyclistDao, GeneralResultDao generalResultDao, StageDao stageDao, CompetitionDao competitionDao) {
        this.resultDao = resultDao;
        this.generalResultDao = generalResultDao;
        this.cyclistDao = cyclistDao;
        this.stageDao = stageDao;
        this.competitionDao = competitionDao;
    }

    @Override
    public Result createResult(UUID cyclistId, UUID competitionId) {

        Competition competition = competitionDao.findById(competitionId).orElseThrow(() -> new EntityNotFoundException("Competition is not exists "));
        Cyclist cyclist = cyclistDao.findById(cyclistId).orElseThrow(() -> new EntityNotFoundException("Cyclist  is not exists "));

        GeneralResultId generalResultId = new GeneralResultId();
        generalResultId.setCompetitionId(competition.getCompetitionId());
        generalResultId.setCyclistId(cyclist.getCyclistId());
        Optional<GeneralResult> generalResult = generalResultDao.findById(generalResultId);

        if (generalResult.isEmpty()) {
             throw new EntityNotFoundException("The cyclist cannot added on this stage because is not register in this competition " + competition.getCompetitionName());
        }

        Set<Stage> stages = competition.getStages();
        for (Stage stage : stages) {
            Result result = new Result();
            ResultId resultId = new ResultId();
            resultId.setCyclistId(cyclist.getCyclistId());
            resultId.setStageId(stage.getStageId());
            result.setResultId(resultId);
            result.setStage(stage);
            result.setCyclist(cyclist);
            resultDao.save(result);
        }
        return null;
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


}
