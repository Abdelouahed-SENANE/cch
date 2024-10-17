package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Competition;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public interface CompetitionService {
    Competition saveCompetition(Competition competition);
    Competition updateCompetition(Competition competition);
    Competition deleteCompetition(Competition competition);
    Set<Competition> getAllCompetitions();

    Set<Competition> getFilteredCompetitions(String place , LocalDate startDate);
}
