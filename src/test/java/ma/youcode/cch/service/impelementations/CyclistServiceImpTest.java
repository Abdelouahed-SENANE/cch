package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.cyclist.CreateCyclistDTO;
import ma.youcode.cch.DTOs.cyclist.CyclistResponseDTO;
import ma.youcode.cch.DTOs.team.TeamResponseDTO;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.mapper.CyclistMapper;
import ma.youcode.cch.repository.interfaces.CyclistDao;
import ma.youcode.cch.service.interfaces.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class CyclistServiceImpTest {

    @Mock
    private CyclistDao cyclistDao;

    @Mock
    private CyclistMapper cyclistMapper;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private CyclistServiceImp cyclistService;

    private Cyclist cyclist;
    private final String TEAM_NOT_FOUND_MESSAGE = "Team Not Found";
    private final String CYCLIST_NOT_FOUND_MESSAGE = "Cyclist Not Found";

    private UUID id;
    private UUID teamId;
    private CyclistResponseDTO responseDTO;
    private TeamResponseDTO teamDTO;

    private CreateCyclistDTO createDTO;
    Set<Cyclist> cyclists = new HashSet<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        teamId = UUID.randomUUID();
        cyclist = new Cyclist();
        cyclist.setFirstName("John");
        createDTO = new CreateCyclistDTO();
        createDTO.setTeamId(teamId);
        responseDTO = new CyclistResponseDTO(id, cyclist.getFirstName());
        teamDTO = new TeamResponseDTO();

        Cyclist cyclist1 = new Cyclist();
        Cyclist cyclist2 = new Cyclist();
        cyclists.add(cyclist);
        cyclists.add(cyclist1);
        cyclists.add(cyclist2);

    }

    @Test
    public void shouldReturnCyclistResponseDTOAfterCreateCyclistSuccessfully() {

        when(teamService.getTeam(createDTO.getTeamId())).thenReturn(teamDTO);
        when(cyclistMapper.toCyclistEntity(createDTO)).thenReturn(cyclist);
        when(cyclistDao.save(cyclist)).thenReturn(cyclist);
        when(cyclistMapper.toResponseDTO(cyclist)).thenReturn(responseDTO);

        CyclistResponseDTO result = cyclistService.createCyclist(createDTO);

        assertEquals(responseDTO, result);
        verify(teamService, atLeastOnce()).getTeam(teamId);
        verify(cyclistMapper, times(1)).toResponseDTO(cyclist);
        verify(cyclistMapper, times(1)).toCyclistEntity(createDTO);
        verify(cyclistDao, atLeastOnce()).save(cyclist);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionAfterCreateCyclistFails() {

        when(teamService.getTeam(createDTO.getTeamId())).thenReturn(null);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> cyclistService.createCyclist(createDTO));

        assertEquals(TEAM_NOT_FOUND_MESSAGE, e.getMessage());
        verify(teamService, atLeastOnce()).getTeam(teamId);

    }

    @Test
    public void shouldReturnCyclistResponseDTOAfterUpdatedCyclistSuccessfully() {

        when(teamService.getTeam(createDTO.getTeamId())).thenReturn(teamDTO);
        when(cyclistDao.findById(id)).thenReturn(Optional.of(cyclist));

        when(cyclistMapper.toCyclistEntity(createDTO)).thenReturn(cyclist);
        when(cyclistDao.update(cyclist)).thenReturn(cyclist);

        when(cyclistMapper.toResponseDTO(cyclist)).thenReturn(responseDTO);

        CyclistResponseDTO result = cyclistService.updateCyclist(id , createDTO);

        assertEquals(responseDTO, result);
        verify(teamService, atLeastOnce()).getTeam(teamId);
        verify(cyclistMapper, times(1)).toResponseDTO(cyclist);
        verify(cyclistMapper, times(1)).toCyclistEntity(createDTO);
        verify(cyclistDao, atLeastOnce()).findById(id);
        verify(cyclistDao, atLeastOnce()).update(cyclist);
    }
    @Test
    public void shouldThrowEntityNotFoundExceptionWhenTeamNotFoundUpdatedCyclistFails() {

        when(teamService.getTeam(createDTO.getTeamId())).thenReturn(null);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> cyclistService.updateCyclist(id , createDTO));

        assertEquals(TEAM_NOT_FOUND_MESSAGE, e.getMessage());
        verify(teamService, atLeastOnce()).getTeam(teamId);

    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenCyclistNotFoundUpdatedCyclistFails() {

        when(teamService.getTeam(createDTO.getTeamId())).thenReturn(teamDTO);
        when(cyclistDao.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> cyclistService.updateCyclist(id , createDTO));

        assertEquals(CYCLIST_NOT_FOUND_MESSAGE, e.getMessage());
        verify(teamService, atLeastOnce()).getTeam(teamId);
        verify(cyclistDao, atLeastOnce()).findById(id);

    }

    @Test
    public void shouldReturnCyclistResponseDTOWhenDeletedCyclistSuccessfully() {
        when(cyclistDao.findById(id)).thenReturn(Optional.of(cyclist));
        doReturn(cyclist).when(cyclistDao).delete(cyclist);
        when(cyclistMapper.toResponseDTO(cyclist)).thenReturn(responseDTO);
        CyclistResponseDTO result = cyclistService.deleteCyclist(id);

        assertNotNull(result);

        assertEquals(result.getFirstName() , responseDTO.getFirstName());
        verify(cyclistDao).findById(id);
        verify(cyclistDao).delete(cyclist);


    }

    @Test
    public void shouldThrowNotFoundExceptionWhenDeletedCyclistFails() {
        when(cyclistDao.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> cyclistService.deleteCyclist(id));
        assertEquals(CYCLIST_NOT_FOUND_MESSAGE , e.getMessage());

        verify(cyclistDao).findById(id);
    }

    @Test
    public void shouldListOfCyclistResponseDTOWhenCGetAllCyclistCalls() {

        when(cyclistDao.findAll()).thenReturn(cyclists);

        List<CyclistResponseDTO> result = cyclistService.getAllCyclists();

        assertNotNull(result);

        assertEquals(result.size() , cyclists.size());

        verify(cyclistDao , atLeastOnce()).findAll();

    }

    @Test
    public void shouldReturnCyclistResponseDTOWhenGetCyclistCallsSuccessfully() {
        when(cyclistDao.findById(id)).thenReturn(Optional.of(cyclist));
        when(cyclistMapper.toResponseDTO(cyclist)).thenReturn(responseDTO);

        CyclistResponseDTO result = cyclistService.getCyclist(id);

        assertNotNull(result);
        assertEquals(result , responseDTO);
        verify(cyclistDao).findById(id);
        verify(cyclistMapper).toResponseDTO(cyclist);

    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetCyclistCallsFails() {
        when(cyclistDao.findById(id)).thenReturn(Optional.empty());
        when(cyclistMapper.toResponseDTO(cyclist)).thenReturn(responseDTO);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class , () -> cyclistService.getCyclist(id));

        assertEquals(e.getMessage() , CYCLIST_NOT_FOUND_MESSAGE);
        verify(cyclistDao).findById(id);
        verifyNoInteractions(cyclistMapper);

    }
    @Test
    public void shouldReturnSortedListCyclistResponseDTOWhenGetSortedCyclistCallsFails() {

        String criteria = "lastName";
        List<Cyclist> sortedList = new ArrayList<>();
        when(cyclistDao.findSortedCyclists(criteria)).thenReturn(sortedList);

        List<CyclistResponseDTO> results = cyclistService.getSortedCyclists(criteria);

        assertNotNull(results);
        assertEquals(results.size() , sortedList.size());

        verify(cyclistDao).findSortedCyclists(criteria);
        verifyNoInteractions(cyclistMapper);

    }





}