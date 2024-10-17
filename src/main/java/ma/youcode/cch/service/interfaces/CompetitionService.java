package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Competition;

import java.util.Set;
import java.util.UUID;

public interface CompetitionService {
    Competition saveCompetition(Competition competition);
    Competition updateCompetition(Competition competition);
    Competition deleteCompetition(Competition competition);
    Set<Competition> getAllCompetitions();
}
