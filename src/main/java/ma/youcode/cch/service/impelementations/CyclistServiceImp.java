package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.daos.interfaces.CyclistDao;
import ma.youcode.cch.dtos.cyclist.CreateCyclistDTO;
import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.mapper.CyclistMapper;
import ma.youcode.cch.service.interfaces.CyclistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CyclistServiceImp implements CyclistService {

    private final CyclistDao cyclistDao;
    private final CyclistMapper cyclistMapper;

    public CyclistServiceImp(CyclistDao cyclistDao, CyclistMapper cyclistMapper) {
        this.cyclistDao = cyclistDao;
        this.cyclistMapper = cyclistMapper;
    }


    @Override
    public CyclistResponseDTO createCyclist(CreateCyclistDTO createCyclistDTO) {

        Cyclist cyclist = cyclistMapper.toCyclistEntity(createCyclistDTO);
        cyclist = cyclistDao.save(cyclist);
        return cyclistMapper.toResponseDTO(cyclist);

    }

    @Override
    public CyclistResponseDTO updateCyclist( UUID cyclistId , CreateCyclistDTO createCyclistDTO) {

        if (!getCyclistById(cyclistId).isPresent()) {
            throw new EntityNotFoundException("Cyclist Not Found");
        }

        Cyclist updated = cyclistMapper.toCyclistEntity(createCyclistDTO);
        updated.setCyclistId(cyclistId);

        return cyclistMapper.toResponseDTO(cyclistDao.update(updated));
    }

    @Override
    public CyclistResponseDTO deleteCyclist(UUID cyclistId) {
        if (!getCyclistById(cyclistId).isPresent()) {
            throw new EntityNotFoundException("Cyclist Not Found");
        }
        Cyclist deleted = this.getCyclistById(cyclistId).orElse(null);
        return cyclistMapper.toResponseDTO(cyclistDao.delete(deleted));
    }

    @Override
    public List<CyclistResponseDTO> getAllCyclists() {
        return this.convertToListCyclistDTO(cyclistDao.findAll());
    }

    private List<CyclistResponseDTO> convertToListCyclistDTO(Set<Cyclist> cyclists) {
        return cyclists.stream().map(cyclistMapper::toResponseDTO).collect(Collectors.toList());
    }
    private List<CyclistResponseDTO> convertToListCyclistDTO(List<Cyclist> cyclists) {
        return cyclists.stream().map(cyclistMapper::toResponseDTO).collect(Collectors.toList());
    }


    @Override
    public Optional<Cyclist> getCyclistById(UUID cyclistId) {
        return cyclistDao.findById(cyclistId);
    }

    @Override
    public CyclistResponseDTO getCyclist(UUID cyclistId) {
        return cyclistMapper.toResponseDTO(getCyclistById(cyclistId).orElse(null));
    }

    @Override
    public List<CyclistResponseDTO> getSortedCyclists(String criteria) {

        return this.convertToListCyclistDTO(cyclistDao.findSortedCyclists(criteria));
    }
}
