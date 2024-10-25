package ma.youcode.cch.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class StageResultId implements Serializable {

    private UUID cyclistId;
    private UUID stageId;

    public StageResultId(){}
    public StageResultId(UUID cyclistId , UUID stageId){
        this.cyclistId = cyclistId;
        this.stageId = stageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageResultId stageResultId = (StageResultId) o;
        return Objects.equals(cyclistId, stageResultId.cyclistId) && Objects.equals(stageId, stageResultId.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistId, stageId);
    }





}
