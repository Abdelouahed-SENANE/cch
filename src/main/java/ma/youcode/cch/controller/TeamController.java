package ma.youcode.cch.controller;

import ma.youcode.cch.dtos.team.CreateTeamDTO;
import ma.youcode.cch.dtos.team.TeamResponseDTO;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @PostMapping
    public ResponseEntity<TeamResponseDTO> addTeam(@RequestBody CreateTeamDTO createTeamDTO) {
        System.out.println(createTeamDTO.toString());
        return ResponseEntity.ok(teamService.createTeam(createTeamDTO));
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable UUID teamId ,  @RequestBody CreateTeamDTO createTeamDTO) {
        createTeamDTO.setTeamId(teamId);
        return ResponseEntity.ok(teamService.updateTeam(createTeamDTO));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable UUID teamId) {
        TeamResponseDTO response = teamService.deleteTeam(teamId);
        String success = response.getTeamName() + " has been deleted successfully.";
        return ResponseEntity.ok(success);

    }
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> getTeam(@PathVariable UUID teamId) {
        TeamResponseDTO response = teamService.getTeam(teamId);
        return ResponseEntity.ok(response);

    }




}
