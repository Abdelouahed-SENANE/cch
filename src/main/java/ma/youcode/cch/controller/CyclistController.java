package ma.youcode.cch.controller;

import ma.youcode.cch.dtos.cyclist.CreateCyclistDTO;
import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/cyclists")
public class CyclistController {

    private final CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @GetMapping
    public ResponseEntity<List<CyclistResponseDTO>> cyclists(){

        List<CyclistResponseDTO> getCyclistsDTOs = cyclistService.getAllCyclists();
        return ResponseEntity.ok(getCyclistsDTOs);
    }

    @PostMapping
    public ResponseEntity<CyclistResponseDTO> addCyclist(@RequestBody CreateCyclistDTO createCyclistDTO){
        return ResponseEntity.ok(cyclistService.createCyclist(createCyclistDTO));
    }

}
