package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.generic.implementations.GenericServiceImp;
import ma.youcode.cch.service.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.UUID;

@Service
public class CompetitionServiceImp extends GenericServiceImp<Competition , UUID> implements CompetitionService {




}
