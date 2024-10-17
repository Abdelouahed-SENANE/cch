package ma.youcode.cch.dao.interfaces;

import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.generic.interfaces.GenericDao;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CyclistDao extends GenericDao<Cyclist, UUID> {

    List<Cyclist> findSortedCyclists(String criteria);
}
