package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.dtos.cyclist.CreateCyclistDTO;
import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.entity.Cyclist;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CyclistService {

    CyclistResponseDTO createCyclist(CreateCyclistDTO createCyclistDTO);
    CyclistResponseDTO updateCyclist( UUID cyclistId , CreateCyclistDTO createCyclistDTO);
    CyclistResponseDTO deleteCyclist(UUID cyclistId);
    List<CyclistResponseDTO> getAllCyclists();
    List<Cyclist> getSortedCyclists(String criteria);
    Cyclist getCyclistById(UUID cyclistId);
    CyclistResponseDTO getCyclist(UUID cyclistId);

//    public String test();

}
