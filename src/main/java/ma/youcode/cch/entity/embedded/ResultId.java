package ma.youcode.cch.entity.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import ma.youcode.cch.entity.Result;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ResultId implements Serializable {

    private UUID cyclistId;
    private UUID stageId;

    public ResultId(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultId resultId = (ResultId) o;
        return Objects.equals(cyclistId, resultId.cyclistId) && Objects.equals(stageId, resultId.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistId, stageId);
    }





}
