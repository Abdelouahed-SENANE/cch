package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.entity.Cyclist;

import java.util.List;
import java.util.Set;

public interface CyclistService {

    Cyclist createCyclist(Cyclist cyclist);
    Cyclist updateCyclist(Cyclist cyclist);
    Cyclist deleteCyclist(Cyclist cyclist);
    List<CyclistResponseDTO> getAllCyclists();
    List<Cyclist> getSortedCyclists(String criteria);

//    public String test();

}
