package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.cch.entity.embedded.ResultId;

import java.time.Duration;

@Entity
@Getter
@Setter
@Table(name = "results")
public class Result {

    @EmbeddedId
    private ResultId resultId;

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

    private Duration duration;


}