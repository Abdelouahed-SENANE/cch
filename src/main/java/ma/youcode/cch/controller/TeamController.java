package ma.youcode.cch.controller;

import ma.youcode.cch.DTOs.team.CreateTeamDTO;
import ma.youcode.cch.DTOs.team.TeamResponseDTO;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> allTeams(){
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    @PostMapping
    public ResponseEntity<TeamResponseDTO> addTeam(@RequestBody CreateTeamDTO createTeamDTO) {
        System.out.println(createTeamDTO.toString());
        return ResponseEntity.ok(teamService.createTeam(createTeamDTO));
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable UUID teamId ,  @RequestBody CreateTeamDTO createTeamDTO) {
        return ResponseEntity.ok(teamService.updateTeam(createTeamDTO , teamId));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> deleteTeam(@PathVariable UUID teamId) {
        TeamResponseDTO response = teamService.deleteTeam(teamId);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> getTeam(@PathVariable UUID teamId) {
        TeamResponseDTO response = teamService.getTeam(teamId);
        return ResponseEntity.ok(response);

    }




}
