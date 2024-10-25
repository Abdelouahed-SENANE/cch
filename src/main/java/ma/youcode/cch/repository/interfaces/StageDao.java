package ma.youcode.cch.repository.interfaces;

import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.generic.interfaces.GenericDao;

import java.util.UUID;

public interface StageDao extends GenericDao<Stage, UUID> {
//    Optional<Stage> findStageOrderedByDuration(UUID id);
}
