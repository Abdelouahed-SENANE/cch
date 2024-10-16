package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.generic.implementations.GenericServiceImp;
import ma.youcode.cch.repository.interfaces.CompetitionRepository;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CyclistServiceImp extends GenericServiceImp<Cyclist, UUID> implements CyclistService {


}
