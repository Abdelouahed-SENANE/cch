package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.dao.interfaces.CyclistDao;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.service.interfaces.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CyclistServiceImp  implements CyclistService {

    private final CyclistDao cyclistDao;

    public CyclistServiceImp(CyclistDao cyclistDao) {this.cyclistDao = cyclistDao;}


    @Override
    public Cyclist saveCyclist(Cyclist cyclist) {
        return cyclistDao.save(cyclist);
    }

    @Override
    public Cyclist updateCyclist(Cyclist cyclist) {
        return cyclistDao.update(cyclist);
    }

    @Override
    public Cyclist deleteCyclist(Cyclist cyclist) {
        return cyclistDao.delete(cyclist);
    }

    @Override
    public Set<Cyclist> findAllCyclists() {
        return cyclistDao.findAll();
    }
}
