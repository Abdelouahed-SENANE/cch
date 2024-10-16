package ma.youcode.cch.repository.implementations;

import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.generic.implementations.GenericRepositoryImp;
import ma.youcode.cch.repository.interfaces.CompetitionRepository;
import ma.youcode.cch.repository.interfaces.CyclistRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CyclistRepositoryImp extends GenericRepositoryImp<Cyclist, UUID> implements CyclistRepository {

}
