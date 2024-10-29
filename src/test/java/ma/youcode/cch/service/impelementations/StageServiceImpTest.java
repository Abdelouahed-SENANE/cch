package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.stage.CreateStageDTO;
import ma.youcode.cch.DTOs.stage.StageResponseDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.entity.StageResult;
import ma.youcode.cch.mapper.StageMapper;
import ma.youcode.cch.repository.interfaces.StageDao;
import ma.youcode.cch.service.interfaces.CompetitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class StageServiceImpTest {

    @Mock
    private StageDao stageDao;

    @Mock
    private StageMapper stageMapper;

    @Mock
    private CompetitionService competitionService;

    @InjectMocks
    private StageServiceImp stageService;

    private Stage stage;
    private final String STAGE_NOT_FOUND_MESSAGE = "Stage Not Found";

    private UUID id;
    private StageResponseDTO responseDTO;
    private Competition competition;

    private CreateStageDTO createDTO;
    Set<Stage> stages = new HashSet<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        stage = new Stage();
        createDTO = new CreateStageDTO();
        createDTO.setCompetitionId(UUID.randomUUID());
        responseDTO = new StageResponseDTO();
        competition = new Competition();
        competition.setNumberOfStage(2);
        competition.setStages(new HashSet<>(Set.of(new Stage(), new Stage())));

        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        stages.add(stage);
        stages.add(stage1);
        stages.add(stage2);

    }

    @Test
    public void shouldReturnStageResponseDTOAfterCreateStageSuccessfully() {

        Competition competition1 = new Competition();
        competition1.setStages(new HashSet<>());
        competition1.setNumberOfStage(2);
        CreateStageDTO createDTO = new CreateStageDTO();

        when(competitionService.getCompetitionById(createDTO.getCompetitionId())).thenReturn(Optional.of(competition1));
        when(stageMapper.toStageEntity(createDTO)).thenReturn(stage);
        when(stageDao.save(stage)).thenReturn(stage);
        when(stageMapper.toResponseDTO(stage)).thenReturn(responseDTO);

        StageResponseDTO result = stageService.createStage(createDTO);

        assertEquals(responseDTO, result);
        verify(competitionService, times(1)).getCompetitionById(createDTO.getCompetitionId());
        verify(stageMapper, times(1)).toResponseDTO(stage);
        verify(stageMapper, times(1)).toStageEntity(createDTO);
        verify(stageDao, atLeastOnce()).save(stage);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionAfterCreateStageFails() {

        when(competitionService.getCompetitionById(createDTO.getCompetitionId())).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> stageService.createStage(createDTO));

        String COMPETITION_NOT_FOUND_MESSAGE = "Competition Not Found";
        assertEquals(COMPETITION_NOT_FOUND_MESSAGE, e.getMessage());
        verify(competitionService, atLeastOnce()).getCompetitionById(createDTO.getCompetitionId());

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionAfterCreateStageFails() {

        when(competitionService.getCompetitionById(createDTO.getCompetitionId())).thenReturn(Optional.of(competition));

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> stageService.createStage(createDTO));

        assertEquals("Cannot add new stage because you have reached the maximum number of stages " + competition.getNumberOfStage(), e.getMessage());
        verify(competitionService, atLeastOnce()).getCompetitionById(createDTO.getCompetitionId());

    }

    @Test
    public void shouldReturnStageResponseDTOAfterUpdatedStageSuccessfully() {

        when(stageDao.findById(id)).thenReturn(Optional.of(stage));

        when(stageMapper.toStageEntity(createDTO)).thenReturn(stage);
        when(stageDao.update(stage)).thenReturn(stage);

        when(stageMapper.toResponseDTO(stage)).thenReturn(responseDTO);

        StageResponseDTO result = stageService.updateStage(id, createDTO);

        assertEquals(responseDTO, result);
        verify(stageMapper, times(1)).toResponseDTO(stage);
        verify(stageMapper, times(1)).toStageEntity(createDTO);
        verify(stageDao, atLeastOnce()).findById(id);
        verify(stageDao, atLeastOnce()).update(stage);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenStageNotFoundUpdatedStageFails() {

        when(stageDao.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> stageService.updateStage(id, createDTO));

        assertEquals(STAGE_NOT_FOUND_MESSAGE, e.getMessage());
        verify(stageDao, atLeastOnce()).findById(id);

    }


    @Test
    public void shouldReturnStageResponseDTOWhenDeletedStageSuccessfully() {
        when(stageDao.findById(id)).thenReturn(Optional.of(stage));
        doReturn(stage).when(stageDao).delete(stage);
        when(stageMapper.toResponseDTO(stage)).thenReturn(responseDTO);
        StageResponseDTO result = stageService.deleteStage(id);

        assertNotNull(result);

        assertEquals(result.getCompetition(), responseDTO.getCompetition());
        verify(stageDao).findById(id);
        verify(stageDao).delete(stage);


    }

    @Test
    public void shouldThrowNotFoundExceptionWhenDeletedStageFails() {
        when(stageDao.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> stageService.deleteStage(id));
        assertEquals(STAGE_NOT_FOUND_MESSAGE, e.getMessage());

        verify(stageDao).findById(id);
    }

    @Test
    public void shouldListOfStageResponseDTOWhenCGetAllStageCalls() {

        when(stageDao.findAll()).thenReturn(stages);

        List<StageResponseDTO> result = stageService.getAllStages();

        assertNotNull(result);

        assertEquals(result.size(), stages.size());

        verify(stageDao, atLeastOnce()).findAll();

    }

    //
    @Test
    public void shouldReturnStageResponseDTOWhenGetStageCallsSuccessfully() {
        when(stageDao.findById(id)).thenReturn(Optional.of(stage));
        when(stageMapper.toResponseDTO(stage)).thenReturn(responseDTO);

        StageResponseDTO result = stageService.getStage(id);

        assertNotNull(result);
        assertEquals(result, responseDTO);
        verify(stageDao).findById(id);
        verify(stageMapper).toResponseDTO(stage);

    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetStageCallsFails() {
        when(stageDao.findById(id)).thenReturn(Optional.empty());
        when(stageMapper.toResponseDTO(stage)).thenReturn(responseDTO);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> stageService.getStage(id));

        assertEquals(e.getMessage(), STAGE_NOT_FOUND_MESSAGE);
        verify(stageDao).findById(id);
        verifyNoInteractions(stageMapper);

    }

    @Test
    public void shouldReturnStageResponseDTOWhenPatchCompletedSuccessfully() {

        createDTO.setCompleted(true);
        when(stageService.getStageEntity(id)).thenReturn(Optional.of(stage));
        when(stageDao.updateIsCompleted(stage)).thenReturn(stage);
        when(stageMapper.toResponseDTO(stage)).thenReturn(responseDTO);

        StageResponseDTO result = stageService.patchCompleted(createDTO, id);

        assertNotNull(result);
        assertEquals(result, responseDTO);
        assertEquals(result.isCompleted(), responseDTO.isCompleted());

        verify(stageDao).updateIsCompleted(stage);
        verify(stageMapper).toResponseDTO(stage);

    }

    @Test
    public void shouldEntityNotFoundExceptionWhenPatchCompletedFails() {

        when(stageService.getStageEntity(id)).thenReturn(Optional.empty());
        when(stageDao.updateIsCompleted(stage)).thenReturn(stage);
        when(stageMapper.toResponseDTO(stage)).thenReturn(responseDTO);
        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> stageService.patchCompleted(createDTO, id));
        assertEquals(e.getMessage(), STAGE_NOT_FOUND_MESSAGE);
        verify(stageDao, times(1)).findById(id);
        verifyNoInteractions(stageMapper);

    }

    @Test
    public void shouldReturnStageWithHisStageResultOrderedByDuration() {


        List<StageResult> stageResults = new ArrayList<>();
        StageResult result1 = new StageResult();
        result1.setStageDuration(Duration.ofMinutes(10));
        StageResult result2 = new StageResult();
        result2.setStageDuration(Duration.ofMinutes(14));
        StageResult result3 = new StageResult();
        result3.setStageDuration(Duration.ofMinutes(2));
        stageResults.add(result1);
        stageResults.add(result2);
        stageResults.add(result3);
        stage.setStageResults(stageResults);
        when(stageDao.findById(id)).thenReturn(Optional.of(stage));

        Optional<Stage> stageOptional = stageService.getStageWithResultOrderedByDuration(id);

        assertTrue(stageOptional.isPresent());

        assertEquals(Duration.ofMinutes(2) , stageOptional.get().getStageResults().get(0).getStageDuration());
        assertEquals(Duration.ofMinutes(10) , stageOptional.get().getStageResults().get(1).getStageDuration());
        assertEquals(Duration.ofMinutes(14) , stageOptional.get().getStageResults().get(2).getStageDuration());

        verify(stageDao).findById(id);


    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetOrderedByDurationIsCalled() {



        when(stageDao.findById(id)).thenReturn(Optional.empty());

        Optional<Stage> result = stageService.getStageWithResultOrderedByDuration(id);

        assertEquals(result , Optional.empty());

        verify(stageDao).findById(id);


    }
}