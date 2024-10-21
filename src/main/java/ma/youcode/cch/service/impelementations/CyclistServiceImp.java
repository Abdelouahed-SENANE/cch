package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.daos.interfaces.CyclistDao;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.service.interfaces.CyclistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CyclistServiceImp  implements CyclistService {

    private final CyclistDao cyclistDao;

    public CyclistServiceImp(CyclistDao cyclistDao) {this.cyclistDao = cyclistDao;}

//    @Override
//    public String test() {
//        return "The  dependency Injection is worked fine";
//    }

    @Override
    public Cyclist createCyclist(Cyclist cyclist) {
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
    public Set<Cyclist> getAllCyclists() {
        return cyclistDao.findAll();
    }

    @Override
    public List<Cyclist> getSortedCyclists(String criteria) {
        return cyclistDao.findSortedCyclists(criteria);
    }
}
