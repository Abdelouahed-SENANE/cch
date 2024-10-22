package ma.youcode.cch.controller;

import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cyclists")
public class CyclistController {

    private final CyclistService cyclistService;
    private final TeamService teamService;

    public CyclistController(CyclistService cyclistService , TeamService teamService) {
        this.cyclistService = cyclistService;
        this.teamService = teamService;
    }

    @GetMapping
    public String test(){
//        Cyclist cyclist = new Cyclist();
//        cyclist.setFirstName("Abderahmane");
//        cyclist.setLastName("GOUDAR");
//        cyclist.setNationality("A");
//        cyclist.setAge("30");
        Team team = new Team();
        team.setTeamName("Movistar Team");
        teamService.createTeam(team);
//        team.setTeamId(UUID.fromString("5e3c2a98-f705-4fee-9d31-3bdd9c52648a"));
//        cyclist.setTeam(team);
//        Cyclist saved = cyclistService.createCyclist(cyclist);
//
//
        return "Hello Controller";
    }


}
