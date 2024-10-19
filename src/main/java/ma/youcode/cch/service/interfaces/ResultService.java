package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Result;

import java.util.Set;
import java.util.UUID;

public interface ResultService {
    Result createResult(UUID cyclistId , UUID competitionId);
    Result updateResult(Result competition);
    Result deleteResult(Result competition);
    Set<Result> getAllResults();

}
