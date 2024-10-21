package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Result;

import java.util.Set;

public interface ResultService {
    Result createResult(Result result);
    Result updateResult(Result competition);
    Result deleteResult(Result competition);
    Set<Result> getAllResults();

}
