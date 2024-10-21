package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.cch.entity.embedded.GeneralResultId;

import java.time.Duration;

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

    @Column(name = "overall_rank")
    private int overallRank;

    @Column(name = "overall_duration")
    private Duration overallDuration;

    public GeneralResult(){}
}
