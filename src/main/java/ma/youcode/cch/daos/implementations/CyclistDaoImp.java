package ma.youcode.cch.daos.implementations;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import ma.youcode.cch.daos.interfaces.CyclistDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CyclistDaoImp extends GenericDaoImp<Cyclist, UUID> implements CyclistDao {

    public CyclistDaoImp(){super(Cyclist.class);}

    @Override
    @Transactional
    public List<Cyclist> findSortedCyclists(String criteria) {
        List<Cyclist> cyclists = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Cyclist> criteriaQuery = criteriaBuilder.createQuery(Cyclist.class);

        Root<Cyclist> cyclistRoot = criteriaQuery.from(Cyclist.class);
        Join<Cyclist , Team> joinTeam = cyclistRoot.join("team");

        switch (criteria) {
            case "lastName" :
                criteriaQuery.select(cyclistRoot)
                        .orderBy(criteriaBuilder.asc(cyclistRoot.get("lastName")));
                break;

            case "nationality" :
                criteriaQuery.select(cyclistRoot)
                        .orderBy(criteriaBuilder.asc(cyclistRoot.get("nationality")));
                break;
            case "teamName" :
                criteriaQuery.select(cyclistRoot)
                        .orderBy(criteriaBuilder.asc(joinTeam.get("teamName")));
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting criteria " + criteria);
        }

        cyclists = session.createQuery(criteriaQuery).getResultList();
        return cyclists;
    }


}
