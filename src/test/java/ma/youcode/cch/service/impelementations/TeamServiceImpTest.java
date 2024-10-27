package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.DTOs.team.CreateTeamDTO;
import ma.youcode.cch.DTOs.team.TeamResponseDTO;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.mapper.TeamMapper;
import ma.youcode.cch.repository.interfaces.TeamDao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceImpTest {

    @Mock
    private TeamDao teamDao;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImp teamService;

    private Team team;
    private final String TEAM_NOT_FOUND = "Team Not Found";
    private UUID id;
    private TeamResponseDTO responseDTO;
    private CreateTeamDTO createDTO;
    Set<Team> teams = new HashSet<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        team = new Team();
        team.setTeamName("Test name");
        createDTO = new CreateTeamDTO();
        createDTO.setTeamName("Test name");
        responseDTO = new TeamResponseDTO(id, team.getTeamName());

        Team team1 = new Team();
        Team team2 = new Team();
        teams.add(team);
        teams.add(team1);
        teams.add(team2);


    }

    @Test
    public void shouldReturnResponseDTOWhenTeamIsSavedSuccessfully() {
        when(teamMapper.toTeamEntity(createDTO)).thenReturn(team);
        when(teamDao.save(team)).thenReturn(team);
        when(teamMapper.toResponseDTO(team)).thenReturn(responseDTO);

        TeamResponseDTO result = teamService.createTeam(createDTO);

        assertEquals(result, responseDTO);
        verify(teamDao, times(1)).save(team);
        verify(teamMapper, times(1)).toTeamEntity(createDTO);
        verify(teamMapper, times(1)).toResponseDTO(team);
    }

    @Test
    public void shouldThrowExceptionWhenCreateTeamFails() {
        when(teamMapper.toTeamEntity(createDTO)).thenReturn(team);
        when(teamDao.save(team)).thenThrow(new RuntimeException("Database Error"));

        assertThrows(RuntimeException.class, () -> {
            teamService.createTeam(createDTO);
        });

        verify(teamDao, times(1)).save(team);
        verify(teamMapper, times(1)).toTeamEntity(createDTO);
    }


    @Test
    public void shouldReturnResponseDTOWhenTeamIsUpdatedSuccessfully() {

        when(teamService.isTeamExist(id)).thenReturn(true);
        when(teamMapper.toTeamEntity(createDTO)).thenReturn(team);
        when(teamDao.update(team)).thenReturn(team);
        when(teamMapper.toResponseDTO(team)).thenReturn(responseDTO);

        TeamResponseDTO result = teamService.updateTeam(createDTO, id);

        assertEquals(result, responseDTO);

        verify(teamMapper, times(1)).toTeamEntity(createDTO);
        verify(teamDao, times(1)).update(team);
        verify(teamMapper, times(1)).toResponseDTO(team);

    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenTeamIsUpdatedFails() {

        when(teamService.isTeamExist(id)).thenReturn(false);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> teamService.updateTeam(createDTO, id));

        assertEquals(TEAM_NOT_FOUND, e.getMessage());
        verifyNoInteractions(teamMapper);
        verify(teamDao, times(1)).existsById(id);

    }

    @Test
    public void shouldReturnResponseDTOWhenTeamIsDeletedSuccessfully() {

        when(teamService.isTeamExist(id)).thenReturn(true);
        when(teamDao.findById(id)).thenReturn(Optional.of(team));
        when(teamDao.delete(team)).thenReturn(team);
        when(teamMapper.toResponseDTO(team)).thenReturn(responseDTO);

        TeamResponseDTO result = teamService.deleteTeam(id);

        assertEquals(result, responseDTO);

        verify(teamDao, times(1)).findById(id);
        verify(teamDao, times(1)).delete(team);
        verify(teamDao, times(1)).existsById(id);


    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenTeamIsDeletedFails() {

        when(teamDao.existsById(id)).thenReturn(false);


        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> {
            teamService.deleteTeam(id);
        });
        assertEquals(TEAM_NOT_FOUND, e.getMessage());

        verify(teamDao, times(1)).existsById(id);
        verifyNoInteractions(teamMapper); // Ensure no interactions with teamMapper occurred

    }

    @Test
    public void shouldReturnListOfTeamResponseDTOWhenGetAllTeamsCalls() {
        when(teamDao.findAll()).thenReturn(teams);

        List<TeamResponseDTO> results = teamService.getAllTeams();

        assertNotNull(results);
        assertEquals(teams.size(), results.size());

    }

    @Test
    public void shouldReturnTeamResponseDTOWhenGetTeamCalls() {

        when(teamDao.findById(id)).thenReturn(Optional.of(team));
        when(teamMapper.toResponseDTO(team)).thenReturn(responseDTO);

        TeamResponseDTO result = teamService.getTeam(id);

        assertEquals(result, responseDTO);

        verify(teamDao, atLeastOnce()).findById(id);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenGetTeamCalls() {

        when(teamDao.findById(id)).thenReturn(Optional.empty());
        when(teamMapper.toResponseDTO(team)).thenReturn(responseDTO);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> teamService.getTeam(id));

        assertEquals(TEAM_NOT_FOUND, e.getMessage());

        verify(teamDao, atLeastOnce()).findById(id);
    }


}