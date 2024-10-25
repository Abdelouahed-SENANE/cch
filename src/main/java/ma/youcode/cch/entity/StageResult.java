package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.cch.entity.embedded.StageResultId;

import java.time.Duration;

@Entity
@Getter
@Setter
@Table(name = "stage_results")
public class StageResult {

    @EmbeddedId
    private StageResultId stageResultId;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("stageId")
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Column(name = "stage_rank")
    private int stageRank;

    @Column(name = "stage_duration")
    private Duration stageDuration;


}
