package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.daos.interfaces.CompetitionDao;
import ma.youcode.cch.daos.interfaces.CyclistDao;
import ma.youcode.cch.daos.interfaces.GeneralResultDao;
import ma.youcode.cch.dtos.generalResult.CreateGeneralResultDTO;
import ma.youcode.cch.dtos.generalResult.GeneralResultResponseDTO;
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

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

        if (!isCyclistAndCompetitionValid(generalResultDTO.getCyclistId() , generalResultDTO.getCompetitionId())) {
            throw new EntityNotFoundException("Validation failed for cyclist and competition.");
        }

        GeneralResult generalResult = generalResultMapper.toGeneralResultEntity(generalResultDTO);

        Optional<GeneralResult> getGeneralResult = generalResultDao.findByGeneralResultId(generalResult.getGeneralResultId());

        if (getGeneralResult.isPresent()) {
            throw new IllegalArgumentException("This " + getGeneralResult.get().getCyclist().getLastName() + " Already  Subscribed On This Competition " + getGeneralResult.get().getCompetition().getCompetitionName());
        }

        return generalResultMapper.toResponseDTO(generalResultDao.save(generalResult));
    }


    private boolean isCyclistAndCompetitionValid(UUID cyclistId , UUID competitionId) {

        Optional<Cyclist> cyclist = cyclistService.getCyclistById(cyclistId);
        Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
        return cyclist.isPresent() && competition.isPresent();
    }



    @Override
    public GeneralResultResponseDTO updateGeneralResult(UUID generalResultId, CreateGeneralResultDTO generalResult) {
        return null;
//        return generalResultDao.update(generalResult);
    }

    @Override
    public GeneralResultResponseDTO deleteGeneralResult(UUID generalResultID) {
        return null;
//        return generalResultDao.delete(generalResult);
    }

    @Override
    public Set<GeneralResult> getAllGeneralResults() {
        return generalResultDao.findAll();
    }


}
