package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Cyclist;

import java.util.Set;
import java.util.UUID;

public interface CyclistService {

    Cyclist saveCyclist(Cyclist cyclist);
    Cyclist updateCyclist(Cyclist cyclist);
    Cyclist deleteCyclist(Cyclist cyclist);
    Set<Cyclist> findAllCyclists();



}
