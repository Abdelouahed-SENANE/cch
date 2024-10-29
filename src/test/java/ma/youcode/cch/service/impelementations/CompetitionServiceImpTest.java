package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.competition.CreateCompetitionDTO;
import ma.youcode.cch.DTOs.competition.CompetitionResponseDTO;
import ma.youcode.cch.DTOs.team.TeamResponseDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.mapper.CompetitionMapper;
import ma.youcode.cch.repository.interfaces.CompetitionDao;
import ma.youcode.cch.service.interfaces.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class CompetitionServiceImpTest {

    @Mock
    private CompetitionDao competitionDao;

    @Mock
    private CompetitionMapper competitionMapper;


    @InjectMocks
    private CompetitionServiceImp competitionService;

    private Competition competition;
    private final String NOT_FOUND_MESSAGE = "Competition Not Found";

    private UUID id;
    private UUID teamId;
    private CompetitionResponseDTO responseDTO;
    private TeamResponseDTO teamDTO;

    private CreateCompetitionDTO createDTO;
    Set<Competition> competitions = new HashSet<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        competition = new Competition();
        createDTO = new CreateCompetitionDTO();
        responseDTO = new CompetitionResponseDTO();

        Competition competition1 = new Competition();
        Competition competition2 = new Competition();
        competitions.add(competition);
        competitions.add(competition1);
        competitions.add(competition2);

    }

    @Test
    public void shouldReturnCompetitionResponseDTOAfterCreateCompetitionSuccessfully() {

        when(competitionMapper.toCompetitionEntity(createDTO)).thenReturn(competition);
        when(competitionDao.save(competition)).thenReturn(competition);
        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);

        CompetitionResponseDTO result = competitionService.createCompetition(createDTO);

        assertEquals(responseDTO, result);

        verify(competitionMapper, times(1)).toResponseDTO(competition);
        verify(competitionMapper, times(1)).toCompetitionEntity(createDTO);
        verify(competitionDao, atLeastOnce()).save(competition);
    }


    @Test
    public void shouldReturnCompetitionResponseDTOAfterUpdatedCompetitionSuccessfully() {

        when(competitionDao.findById(id)).thenReturn(Optional.of(competition));

        when(competitionMapper.toCompetitionEntity(createDTO)).thenReturn(competition);
        when(competitionDao.update(competition)).thenReturn(competition);

        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);

        CompetitionResponseDTO result = competitionService.updateCompetition(id , createDTO);

        assertEquals(responseDTO, result);
        verify(competitionMapper, times(1)).toResponseDTO(competition);
        verify(competitionMapper, times(1)).toCompetitionEntity(createDTO);
        verify(competitionDao, atLeastOnce()).findById(id);
        verify(competitionDao, atLeastOnce()).update(competition);
    }


    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCompetitionNotFoundUpdatedCompetitionFails() {

        when(competitionDao.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> competitionService.updateCompetition(id , createDTO));

        assertEquals(NOT_FOUND_MESSAGE, e.getMessage());
        verify(competitionDao, atLeastOnce()).findById(id);

    }

    @Test
    public void shouldReturnCompetitionResponseDTOWhenDeletedCompetitionSuccessfully() {
        when(competitionDao.findById(id)).thenReturn(Optional.of(competition));
        doReturn(competition).when(competitionDao).delete(competition);
        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);
        CompetitionResponseDTO result = competitionService.deleteCompetition(id);

        assertNotNull(result);

        assertEquals(result , responseDTO);
        verify(competitionDao , times(2)).findById(id);
        verify(competitionDao).delete(competition);

    }

    @Test
    public void shouldThrowNotFoundExceptionWhenDeletedCompetitionFails() {
        when(competitionDao.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> competitionService.deleteCompetition(id));
        assertEquals(NOT_FOUND_MESSAGE , e.getMessage());

        verify(competitionDao).findById(id);
    }
//
    @Test
    public void shouldListOfCompetitionResponseDTOWhenCGetAllCompetitionCalls() {

        when(competitionDao.findAll()).thenReturn(competitions);

        List<CompetitionResponseDTO> result = competitionService.getAllCompetitions();

        assertNotNull(result);

        assertEquals(result.size() , competitions.size());

        verify(competitionDao , atLeastOnce()).findAll();

    }

    @Test
    public void shouldReturnCompetitionResponseDTOWhenGetCompetitionCallsSuccessfully() {
        when(competitionDao.findById(id)).thenReturn(Optional.of(competition));
        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);

        CompetitionResponseDTO result = competitionService.getCompetition(id);

        assertNotNull(result);
        assertEquals(result , responseDTO);
        verify(competitionDao).findById(id);
        verify(competitionMapper).toResponseDTO(competition);

    }
//
    @Test
    public void shouldThrowNotFoundExceptionWhenGetCompetitionCallsFails() {
        when(competitionDao.findById(id)).thenReturn(Optional.empty());
        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> competitionService.getCompetition(id));

        assertEquals(e.getMessage() , NOT_FOUND_MESSAGE);
        verify(competitionDao).findById(id);
        verifyNoInteractions(competitionMapper);

    }

    @Test
    public void shouldReturnListCompetitionResponseDTOWhenCallFilteredCompetitions() {
        String place = "Maroc";
        LocalDate startDate = LocalDate.of(2024 , 12, 22);
        when(competitionDao.findFilteredCompetitions(place , startDate)).thenReturn(competitions);

        List<CompetitionResponseDTO> results = competitionService.getFilteredCompetitions(place , startDate);
        assertNotNull(results);
        assertEquals(results.size() , competitions.size());
        verify(competitionDao).findFilteredCompetitions(place , startDate);

    }





}