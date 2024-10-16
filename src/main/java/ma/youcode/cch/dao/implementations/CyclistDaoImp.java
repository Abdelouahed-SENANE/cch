package ma.youcode.cch.dao.implementations;

import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import ma.youcode.cch.dao.interfaces.CyclistDao;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CyclistDaoImp extends GenericDaoImp<Cyclist, UUID> implements CyclistDao {
    public CyclistDaoImp(){super(Cyclist.class);}

}
