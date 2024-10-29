package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.cyclist.CyclistResponseDTO;
import ma.youcode.cch.DTOs.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.DTOs.stage.StageResponseDTO;
import ma.youcode.cch.DTOs.stageResult.CreateStageResultDTO;
import ma.youcode.cch.DTOs.stageResult.StageResultResponseDTO;
import ma.youcode.cch.entity.*;
import ma.youcode.cch.entity.embedded.StageResultId;
import ma.youcode.cch.mapper.StageResultMapper;
import ma.youcode.cch.repository.interfaces.CyclistDao;
import ma.youcode.cch.repository.interfaces.StageDao;
import ma.youcode.cch.repository.interfaces.StageResultDao;
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

class StageResultServiceImpTest {

    @Mock
    private StageResultDao stageResultDao;
    @Mock
    private StageResultMapper stageResultMapper;
    @Mock
    private StageService stageService;
    @Mock
    private StageDao stageDao;
    @Mock
    private GeneralResultService generalResultService;
    @Mock
    private CyclistService cyclistService;
    @Mock
    private CyclistDao cyclistDao;
    @InjectMocks
    private StageResultServiceImp stageResultService;

    private final StageResult stageResult = new StageResult();
    private final StageResultId stageResultId = new StageResultId();
    private final Cyclist cyclist = new Cyclist();
    private final CyclistResponseDTO cyclistDTO = new CyclistResponseDTO();
    private final Stage stage = new Stage();
    private final StageResponseDTO stageDTO = new StageResponseDTO();
    private final String NOT_FOUND = "Result Stage Not Found";
    private final GeneralResult generalResult = new GeneralResult();
    private final GeneralResultResponseDTO generalResultDTO = new GeneralResultResponseDTO();
    private final StageResultResponseDTO responseDTO = new StageResultResponseDTO();
    private final CreateStageResultDTO createDTO = new CreateStageResultDTO();
    Set<StageResult> stageResults = new HashSet<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cyclist.setCyclistId(UUID.randomUUID());
        stage.setStageId(UUID.randomUUID());
        stageResult.setStage(stage);
        stageResult.setCyclist(cyclist);
        stageResultId.setCyclistId(cyclist.getCyclistId());
        stageResultId.setStageId(stage.getStageId());
        stageResult.setStageResultId(stageResultId);
    }

    @Test
    public void shouldReturnStageResultResponseDTOWhenCreateStageResultIsCalled() {

        Competition competition = new Competition();
        competition.setCompetitionId(UUID.randomUUID());
        competition.setCompetitionName("test");
        stage.setCompetition(competition);

        createDTO.setCyclistId(cyclist.getCyclistId());
        createDTO.setStageId(stage.getStageId());
        stageResult.setStageResultId(stageResultId);

        when(stageResultMapper.toStageResultEntity(createDTO)).thenReturn(stageResult);
        when(stageResultDao.findById(stageResultId)).thenReturn(Optional.empty());
        when(stageService.getStageEntity(stageResult.getStage().getStageId())).thenReturn(Optional.of(stage));
        when(cyclistService.getCyclistById(stageResult.getCyclist().getCyclistId())).thenReturn(Optional.of(cyclist));
        when(generalResultService.getGeneralResult(cyclist.getCyclistId() , stage.getCompetition().getCompetitionId())).thenReturn(generalResultDTO);
        when(stageResultDao.save(stageResult)).thenReturn(stageResult);
        when(stageResultMapper.toResponseDTO(stageResult)).thenReturn(responseDTO);

        StageResultResponseDTO result = stageResultService.createStageResult(createDTO);

//        assertNotNull(result);
        assertEquals(result , responseDTO);
        verify(stageService).getStageEntity(stage.getStageId());
        verify(cyclistService).getCyclistById(cyclist.getCyclistId());
        verify(generalResultService).getGeneralResult(cyclist.getCyclistId() , stage.getCompetition().getCompetitionId());
        verify(stageResultDao).save(stageResult);

    }

    @Test
    public void shouldThrowEntityExistsExceptionAfterCreateStageResultFails() {

        when(stageResultMapper.toStageResultEntity(createDTO)).thenReturn(stageResult);
        when(stageResultDao.findById(stageResult.getStageResultId())).thenReturn(Optional.of(stageResult));

        EntityExistsException e = assertThrows(EntityExistsException.class, () -> stageResultService.createStageResult(createDTO));

        assertEquals("The stage result Already Exists", e.getMessage());
        verify(stageResultMapper, atLeastOnce()).toStageResultEntity(createDTO);

    }

    @Test
    public void shouldThrowEntityNotFoundExceptionAfterCreateStageResultFails() {

        when(stageResultMapper.toStageResultEntity(createDTO)).thenReturn(stageResult);
        when(stageResultDao.findById(stageResult.getStageResultId())).thenReturn(Optional.empty());
        when(stageService.getStageEntity(stage.getStageId())).thenReturn(Optional.empty());
        when(cyclistService.getCyclistById(cyclist.getCyclistId())).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> stageResultService.createStageResult(createDTO));

        assertEquals("Stage or Cyclist Not Found", e.getMessage());
        verify(stageResultMapper, atLeastOnce()).toStageResultEntity(createDTO);
        verify(stageService , atLeastOnce()).getStageEntity(stage.getStageId());
        verify(cyclistService , atLeastOnce()).getCyclistById(cyclist.getCyclistId());

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionAfterCreateStageResultFails() {
        Competition competition = new Competition();
        competition.setCompetitionId(UUID.randomUUID());
        competition.setCompetitionName("test");
        stage.setCompetition(competition);

        when(stageResultMapper.toStageResultEntity(createDTO)).thenReturn(stageResult);
        when(stageResultDao.findById(stageResult.getStageResultId())).thenReturn(Optional.empty());
        when(stageService.getStageEntity(stage.getStageId())).thenReturn(Optional.of(stage));
        when(cyclistService.getCyclistById(cyclist.getCyclistId())).thenReturn(Optional.of(cyclist));
        when(generalResultService.getGeneralResult(cyclist.getCyclistId() , stage.getCompetition().getCompetitionId())).thenReturn(null);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> stageResultService.createStageResult(createDTO));

        assertEquals("The cyclist cannot added on this stage because is not subscribed on this competition " + stage.getCompetition().getCompetitionName(), e.getMessage());
        verify(stageResultMapper, atLeastOnce()).toStageResultEntity(createDTO);
        verify(stageService , atLeastOnce()).getStageEntity(stage.getStageId());
        verify(cyclistService , atLeastOnce()).getCyclistById(cyclist.getCyclistId());

    }

    @Test
    public void shouldReturnStageResultResponseDTOWhenUpdateStageResultIsCalled(){
        stage.setCompleted(false);
        when(stageResultDao.findById(stageResultId)).thenReturn(Optional.of(stageResult));
        when(stageResultMapper.toStageResultEntity(createDTO)).thenReturn(stageResult);
        when(stageResultDao.update(stageResult)).thenReturn(stageResult);
        when(stageResultMapper.toResponseDTO(stageResult)).thenReturn(responseDTO);

        StageResultResponseDTO result = stageResultService.updateStageResult(createDTO , cyclist.getCyclistId() , stage.getStageId());

        assertNotNull(result);
        assertEquals(result.getCyclist() , responseDTO.getCyclist());
        verify(stageResultDao).findById(stageResultId);
        verify(stageResultMapper).toStageResultEntity(createDTO);
        verify(stageResultDao).update(stageResult);
        verify(stageResultMapper).toResponseDTO(stageResult);

    }

    @Test
    public void shouldThrowEntityNotFoundExceptionAfterUpdateStageResultFails() {

        when(stageResultDao.findById(stageResult.getStageResultId())).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> stageResultService.updateStageResult(createDTO , cyclist.getCyclistId() , stage.getStageId()));

        assertEquals(NOT_FOUND, e.getMessage());
        verify(stageResultDao , atLeastOnce()).findById(stageResultId);

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionAfterUpdateStageResultFails() {

        when(stageResultDao.findById(stageResult.getStageResultId())).thenReturn(Optional.of(stageResult));
        stage.setCompleted(true);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> stageResultService.updateStageResult(createDTO , cyclist.getCyclistId() , stage.getStageId()));

        assertEquals("Cannot update this Stage result because is Completed", e.getMessage());
        verify(stageResultDao , atLeastOnce()).findById(stageResultId);

    }

    @Test
    public void shouldReturnStageResultResponseDTOWhenDeletedStageResultSuccessfully() {
        when(stageResultDao.findById(stageResultId)).thenReturn(Optional.of(stageResult));

        doReturn(stageResult).when(stageResultDao).delete(stageResult);
        when(stageResultMapper.toResponseDTO(stageResult)).thenReturn(responseDTO);
        StageResultResponseDTO result = stageResultService.deleteStageResult(cyclist.getCyclistId() , stage.getStageId());

        assertNotNull(result);

        assertEquals(result.getCyclist(), responseDTO.getCyclist());
        verify(stageResultDao).delete(stageResult);
        verify(stageResultMapper).toResponseDTO(stageResult);


    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenDeletedStageResultSuccessfully(){
        when(stageResultDao.findById(stageResultId)).thenReturn(Optional.empty());
        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> stageResultService.deleteStageResult(cyclist.getCyclistId() , stage.getStageId()));
        assertEquals(e.getMessage() , NOT_FOUND);
    }

    @Test
    public void shouldReturnListStageResultResponseDTOWhenGetAllStageResultsIsCalled(){

        StageResult stageResult1 = new StageResult();
        StageResult stageResult2 = new StageResult();
        StageResult stageResult3 = new StageResult();
        stageResults.add(stageResult1);
        stageResults.add(stageResult2);
        stageResults.add(stageResult3);

        when(stageResultDao.findAll()).thenReturn(stageResults);
        List<StageResultResponseDTO> results = stageResultService.getAllStageResults();

        assertNotNull(results);
        assertEquals(results.size() , stageResults.size());
        verify(stageResultDao).findAll();
    }

    @Test
    public void shouldReturnStageResultResponseDTOWhenGetStageResultIsCalled(){
        when(stageResultDao.findById(stageResultId)).thenReturn(Optional.of(stageResult));
        when(stageResultMapper.toResponseDTO(stageResult)).thenReturn(responseDTO);

        StageResultResponseDTO result = stageResultService.getStageResult(cyclist.getCyclistId() , stage.getStageId());

        assertNotNull(result);
        assertEquals(result.getCyclist() , responseDTO.getCyclist());
        assertEquals(result.getStage() , responseDTO.getStage());

        verify(stageResultDao).findById(stageResultId);
        verify(stageResultMapper).toResponseDTO(stageResult);


    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenGetStageResultIsCalled(){
        when(stageResultDao.findById(stageResultId)).thenReturn(Optional.empty());
        when(stageResultMapper.toResponseDTO(stageResult)).thenReturn(responseDTO);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> stageResultService.getStageResult(cyclist.getCyclistId() , stage.getStageId()));

        assertNotNull(e);
        assertEquals(e.getMessage() , NOT_FOUND);

        verify(stageResultDao).findById(stageResultId);
        verifyNoInteractions(stageResultMapper);


    }
}