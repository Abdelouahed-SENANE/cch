package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.cch.entity.embedded.GeneralResultId;

import java.time.Duration;
import java.util.Objects;

@Entity
@Table(name = "general_results")
@Getter
@Setter
public class GeneralResult {

    @EmbeddedId
    private GeneralResultId generalResultId;


    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Column(name = "general_rank")
    private int generalRank;


    private Duration duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralResult that = (GeneralResult) o;
        return Objects.equals(generalResultId, that.generalResultId) && Objects.equals(cyclist, that.cyclist) && Objects.equals(competition, that.competition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generalResultId, cyclist, competition);
    }
}
