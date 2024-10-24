package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.daos.interfaces.CyclistDao;
import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.mapper.CyclistMapper;
import ma.youcode.cch.service.interfaces.CyclistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CyclistServiceImp implements CyclistService {

    private final CyclistDao cyclistDao;
    private final CyclistMapper cyclistMapper;

    public CyclistServiceImp(CyclistDao cyclistDao, CyclistMapper cyclistMapper) {
        this.cyclistDao = cyclistDao;
        this.cyclistMapper = cyclistMapper;
    }

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
    public List<CyclistResponseDTO> getAllCyclists() {
        return this.convertToListCyclistDTO(cyclistDao.findAll());
    }

    private List<CyclistResponseDTO> convertToListCyclistDTO(Set<Cyclist> cyclists) {
        return cyclists.stream().map(cyclistMapper::toResponseDTO).collect(Collectors.toList());
    }



    @Override
    public List<Cyclist> getSortedCyclists(String criteria) {
        return cyclistDao.findSortedCyclists(criteria);
    }
}
