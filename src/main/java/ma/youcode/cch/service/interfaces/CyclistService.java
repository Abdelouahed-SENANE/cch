package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.DTOs.cyclist.CreateCyclistDTO;
import ma.youcode.cch.DTOs.cyclist.CyclistResponseDTO;
import ma.youcode.cch.entity.Cyclist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CyclistService {

    CyclistResponseDTO createCyclist(CreateCyclistDTO createCyclistDTO);
    CyclistResponseDTO updateCyclist( UUID cyclistId , CreateCyclistDTO createCyclistDTO);
    CyclistResponseDTO deleteCyclist(UUID cyclistId);
    List<CyclistResponseDTO> getAllCyclists();
    List<CyclistResponseDTO> getSortedCyclists(String criteria);
    Optional<Cyclist> getCyclistById(UUID cyclistId);
    CyclistResponseDTO getCyclist(UUID cyclistId);


}
