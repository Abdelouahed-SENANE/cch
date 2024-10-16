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
public class GeneralResultId implements Serializable {

    private UUID cyclistId;
    private UUID competitionId;

    public GeneralResultId(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralResultId that = (GeneralResultId) o;
        return Objects.equals(cyclistId, that.cyclistId) && Objects.equals(competitionId, that.competitionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistId, competitionId);
    }
}
