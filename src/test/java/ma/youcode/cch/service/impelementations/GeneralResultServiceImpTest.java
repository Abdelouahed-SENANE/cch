package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.cyclist.CyclistResponseDTO;
import ma.youcode.cch.DTOs.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.DTOs.stage.StageResponseDTO;
import ma.youcode.cch.DTOs.generalResult.CreateGeneralResultDTO;
import ma.youcode.cch.DTOs.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.DTOs.stageResult.StageResultResponseDTO;
import ma.youcode.cch.entity.*;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.mapper.GeneralResultMapper;
import ma.youcode.cch.repository.interfaces.CyclistDao;
import ma.youcode.cch.repository.interfaces.StageDao;
import ma.youcode.cch.repository.interfaces.GeneralResultDao;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.GeneralResultService;
import ma.youcode.cch.service.interfaces.StageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class GeneralResultServiceImpTest {

    @InjectMocks
    private GeneralResultServiceImp generalResultService;
    @Mock
    private GeneralResultDao generalResultDao;
    @Mock
    private GeneralResultMapper generalResultMapper;
    @Mock
    private CompetitionService competitionService;
    @Mock
    private CyclistService cyclistService;


    private GeneralResult generalResult = new GeneralResult();
    private final GeneralResultId generalResultId = new GeneralResultId();
    private final Cyclist cyclist = new Cyclist();
    private final CyclistResponseDTO cyclistDTO = new CyclistResponseDTO();
    private final Competition competition = new Competition();
    private final StageResponseDTO stageDTO = new StageResponseDTO();
    private final String NOT_FOUND = "General Result Not Found";
    private final GeneralResultResponseDTO responseDTO = new GeneralResultResponseDTO();
    private final CreateGeneralResultDTO createDTO = new CreateGeneralResultDTO();
    Set<GeneralResult> generalResults = new HashSet<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cyclist.setCyclistId(UUID.randomUUID());
        competition.setCompetitionId(UUID.randomUUID());

        generalResult.setCompetition(competition);
        generalResult.setCyclist(cyclist);

        generalResultId.setCyclistId(cyclist.getCyclistId());
        generalResultId.setCompetitionId(competition.getCompetitionId());

        generalResult.setGeneralResultId(generalResultId);

    }

    @Test
    public void shouldReturnGeneralResultResponseDTOWhenCreateGeneralResultIsCalled() {

        createDTO.setCyclistId(cyclist.getCyclistId());
        createDTO.setCompetitionId(competition.getCompetitionId());

        when(competitionService.getCompetitionById(createDTO.getCompetitionId())).thenReturn(Optional.of(competition));
        when(cyclistService.getCyclistById(createDTO.getCyclistId())).thenReturn(Optional.of(cyclist));
        when(generalResultMapper.toGeneralResultEntity(createDTO)).thenReturn(generalResult);
        when(generalResultDao.findById(generalResultId)).thenReturn(Optional.empty());
        when(generalResultDao.save(generalResult)).thenReturn(generalResult);
        when(generalResultMapper.toResponseDTO(generalResult)).thenReturn(responseDTO);

        GeneralResultResponseDTO result = generalResultService.createGeneralResult(createDTO);

        assertNotNull(result);
        assertEquals(result, responseDTO);
        verify(competitionService, atLeastOnce()).getCompetitionById(generalResultId.getCompetitionId());
        verify(cyclistService, atLeastOnce()).getCyclistById(generalResultId.getCyclistId());
        verify(generalResultMapper, times(1)).toGeneralResultEntity(createDTO);
        verify(generalResultDao, times(1)).findById(generalResultId);
        verify(generalResultDao, times(1)).save(generalResult);
        verify(generalResultMapper).toResponseDTO(generalResult);

    }

    @Test
    public void shouldThrowEntityExistsExceptionWhenCompetitionAndCyclistNotExistsAfterCreateGeneralResultFails() {

        createDTO.setCyclistId(cyclist.getCyclistId());
        createDTO.setCompetitionId(competition.getCompetitionId());

        when(competitionService.getCompetitionById(createDTO.getCompetitionId())).thenReturn(Optional.empty());
        when(cyclistService.getCyclistById(createDTO.getCyclistId())).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> generalResultService.createGeneralResult(createDTO));

        assertEquals("Validation failed for cyclist and competition.", e.getMessage());
        verify(competitionService, atLeastOnce()).getCompetitionById(createDTO.getCompetitionId());
        verify(cyclistService, atLeastOnce()).getCyclistById(createDTO.getCyclistId());

    }

    @Test
    public void shouldReturnThrowIllegalExceptionWhenCreateGeneralResultIsCalled() {

        createDTO.setCyclistId(cyclist.getCyclistId());
        createDTO.setCompetitionId(competition.getCompetitionId());

        when(competitionService.getCompetitionById(createDTO.getCompetitionId())).thenReturn(Optional.of(competition));
        when(cyclistService.getCyclistById(createDTO.getCyclistId())).thenReturn(Optional.of(cyclist));
        when(generalResultMapper.toGeneralResultEntity(createDTO)).thenReturn(generalResult);
        when(generalResultDao.findById(generalResultId)).thenReturn(Optional.of(generalResult));
        when(generalResultDao.save(generalResult)).thenReturn(generalResult);
        when(generalResultMapper.toResponseDTO(generalResult)).thenReturn(responseDTO);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class , () -> {
            generalResultService.createGeneralResult(createDTO);
        });


        assertNotNull(e);

        assertEquals(e.getMessage(), "This " + generalResult.getCyclist().getLastName() + " Already Subscribed On This Competition " + generalResult.getCompetition().getCompetitionName());
        verify(competitionService, atLeastOnce()).getCompetitionById(generalResultId.getCompetitionId());
        verify(cyclistService, atLeastOnce()).getCyclistById(generalResultId.getCyclistId());
        verify(generalResultMapper, times(1)).toGeneralResultEntity(createDTO);
        verify(generalResultDao, times(1)).findById(generalResultId);

    }

    @Test
    public void shouldReturnGeneralResultResponseDTOWhenUpdateGeneralResultIsCalled() {
        when(generalResultDao.findById(generalResultId)).thenReturn(Optional.of(generalResult));
        when(generalResultMapper.toGeneralResultEntity(createDTO)).thenReturn(generalResult);
        when(generalResultDao.update(generalResult)).thenReturn(generalResult);
        when(generalResultMapper.toResponseDTO(generalResult)).thenReturn(responseDTO);

        GeneralResultResponseDTO result = generalResultService.updateGeneralResult(cyclist.getCyclistId(), competition.getCompetitionId(), createDTO);

        assertNotNull(result);
        assertEquals(result.getCyclist(), responseDTO.getCyclist());
        verify(generalResultDao).findById(generalResultId);
        verify(generalResultMapper).toGeneralResultEntity(createDTO);
        verify(generalResultDao).update(generalResult);
        verify(generalResultMapper).toResponseDTO(generalResult);

    }


    @Test
    public void shouldThrowNotFoundExceptionWhenUpdateGeneralResultIsCalled() {
        UUID cyclistId = UUID.randomUUID();
        UUID competitionId = UUID.randomUUID();
        GeneralResultId id = new GeneralResultId(cyclistId , competitionId);

        when(generalResultDao.findById(id)).thenReturn(Optional.empty());
        when(generalResultMapper.toGeneralResultEntity(createDTO)).thenReturn(generalResult);
        when(generalResultDao.update(generalResult)).thenReturn(generalResult);
        when(generalResultMapper.toResponseDTO(generalResult)).thenReturn(responseDTO);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> {
            generalResultService.updateGeneralResult(competitionId , cyclistId , createDTO);
        });

        assertNotNull(e);
        assertEquals(e.getMessage(), NOT_FOUND);
        verifyNoInteractions(generalResultMapper);
        verifyNoInteractions(generalResultMapper);

    }

    @Test
    public void shouldReturnGeneralResultResponseDTOWhenDeleteGeneralResultIsCalled() {

    when(generalResultDao.findById(generalResultId)).thenReturn(Optional.of(generalResult));
     when(generalResultDao.delete(generalResult)).thenReturn(generalResult);
    when(generalResultMapper.toResponseDTO(generalResult)).thenReturn(responseDTO);

    GeneralResultResponseDTO result = generalResultService.deleteGeneralResult(generalResultId.getCyclistId(), generalResultId.getCompetitionId());

    assertNotNull(result);
    assertEquals(result.getCyclist(), responseDTO.getCyclist());
    verify(generalResultDao).findById(generalResultId);
    verify(generalResultDao).delete(generalResult);
    verify(generalResultMapper).toResponseDTO(generalResult);

}


    @Test
    public void shouldThrowNotFoundExceptionWhenDeletedGeneralResultIsCalled() {
        UUID cyclistId = UUID.randomUUID();
        UUID competitionId = UUID.randomUUID();
        GeneralResultId id = new GeneralResultId(cyclistId , competitionId);

        when(generalResultDao.findById(id)).thenReturn(Optional.empty());
        when(generalResultDao.delete(generalResult)).thenReturn(generalResult);
        when(generalResultMapper.toResponseDTO(generalResult)).thenReturn(responseDTO);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> {
            generalResultService.deleteGeneralResult(competitionId , cyclistId);
        });

        assertNotNull(e);
        assertEquals(e.getMessage(), NOT_FOUND);
        verifyNoInteractions(generalResultMapper);

    }

    @Test
    public void shouldReturnListGeneralResultResponseDTOWhenGetAllGeneralResultsIsCalled(){

        GeneralResult generalResult1 = new GeneralResult();
        GeneralResult generalResult2 = new GeneralResult();
        GeneralResult generalResult3 = new GeneralResult();
        generalResults.add(generalResult1);
        generalResults.add(generalResult2);
        generalResults.add(generalResult3);

        when(generalResultDao.findAll()).thenReturn(generalResults);
        List<GeneralResultResponseDTO> results = generalResultService.getAllGeneralResults();

        assertNotNull(results);
        assertEquals(results.size() , generalResults.size());
        verify(generalResultDao).findAll();
    }

    @Test
    public void shouldReturnGeneralResultResponseDTOWhenGetGeneralResultIsCalled(){
        when(generalResultDao.findById(generalResultId)).thenReturn(Optional.of(generalResult));
        when(generalResultMapper.toResponseDTO(generalResult)).thenReturn(responseDTO);

        GeneralResultResponseDTO result = generalResultService.getGeneralResult(cyclist.getCyclistId() , competition.getCompetitionId());

        assertNotNull(result);
        assertEquals(result.getCyclist() , responseDTO.getCyclist());
        assertEquals(result.getCompetition() , responseDTO.getCompetition());

        verify(generalResultDao).findById(generalResultId);
        verify(generalResultMapper).toResponseDTO(generalResult);


    }



}