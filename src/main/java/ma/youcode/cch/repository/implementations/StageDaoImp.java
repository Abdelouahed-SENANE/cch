package ma.youcode.cch.repository.implementations;

import ma.youcode.cch.repository.interfaces.StageDao;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.UUID;


@Repository
@Transactional
public class StageDaoImp extends GenericDaoImp<Stage, UUID> implements StageDao {

    public StageDaoImp(){super(Stage.class);}

    @Override
    public Stage updateIsCompleted(Stage stage) {
        if (stage == null){return null;}
        Session session = sessionFactory.getCurrentSession();
        return session.merge(stage);

    }
}
