package ma.youcode.cch.daos.implementations;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ma.youcode.cch.daos.interfaces.CompetitionDao;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Repository
public class CompetitionDaoImp extends GenericDaoImp<Competition, UUID> implements CompetitionDao {

    public CompetitionDaoImp() {
        super(Competition.class);
    }

    @Override
    @Transactional
    public Set<Competition> findFilteredCompetitions(String place, LocalDate startDate) {
        Set<Competition> competitions = null;
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Competition> criteriaQuery = criteriaBuilder.createQuery(Competition.class);
        Root<Competition> competitionRoot = criteriaQuery.from(Competition.class);

        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null) {
            predicates.add(criteriaBuilder.equal(competitionRoot.get("startDate") , startDate));
        }
        if (place != null && !place.trim().isEmpty()) {
            predicates.add(criteriaBuilder.equal(competitionRoot.get("place") , place));
        }
        criteriaQuery.select(competitionRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        Query<Competition> query = session.createQuery(criteriaQuery);
        competitions = new HashSet<>(query.getResultList());

        return competitions;
    }
}
