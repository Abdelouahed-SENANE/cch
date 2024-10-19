package ma.youcode.cch.daos.implementations;

import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import ma.youcode.cch.daos.interfaces.StageDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class StageDaoImp extends GenericDaoImp<Stage, UUID> implements StageDao {

    public StageDaoImp(){super(Stage.class);}

//    @Override
//    @Transactional
//    public List<Stage> findStagesByCompetitionId(UUID competitionId) {
//
//        Session session = sessionFactory.getCurrentSession();
//        String queryStr = "FROM Stage s WHERE s.competition.competitionId = :competitionId ";
//        Query<Stage>  query = session.createQuery(queryStr , Stage.class);
//        query.setParameter("competitionId" , competitionId);
//        return query.getResultList();
//    }
}
