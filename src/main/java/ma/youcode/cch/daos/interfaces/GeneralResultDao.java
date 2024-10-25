package ma.youcode.cch.daos.interfaces;

import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.generic.interfaces.GenericDao;

import java.util.Optional;

public interface GeneralResultDao extends GenericDao<GeneralResult, GeneralResultId> {

    Optional<GeneralResult> findByGeneralResultId(GeneralResultId generalResultId);

}
