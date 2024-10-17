package ma.youcode.cch.dao.interfaces;

import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.generic.interfaces.GenericDao;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public interface CompetitionDao extends GenericDao<Competition , UUID> {

    Set<Competition> findFilteredCompetitions(String place , LocalDate startDate);
}
