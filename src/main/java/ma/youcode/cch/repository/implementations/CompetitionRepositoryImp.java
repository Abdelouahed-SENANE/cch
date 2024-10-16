package ma.youcode.cch.repository.implementations;

import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.generic.implementations.GenericRepositoryImp;
import ma.youcode.cch.generic.interfaces.GenericRepository;
import ma.youcode.cch.repository.interfaces.CompetitionRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CompetitionRepositoryImp extends GenericRepositoryImp<Competition, UUID> implements CompetitionRepository {



}
