package ma.youcode.cch.controller;

import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
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
    public ResponseEntity<List<CyclistResponseDTO>> cyclists(){

        List<CyclistResponseDTO> getCyclistsDTOs = cyclistService.getAllCyclists();
        return ResponseEntity.ok(getCyclistsDTOs);

    }


}
