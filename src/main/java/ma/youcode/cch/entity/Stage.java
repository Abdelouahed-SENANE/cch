package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "stages")
@Getter
@Setter
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stage_id")
    private UUID stageId;

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "end_location")
    private String endLocation;

    @Column(name = "stage_number")
    private int stageNumber;

    @Column(name = "stage_type")
    private String stageType;

    @Column(name = "is_completed" , columnDefinition = "boolean default false")
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "competition_id" , nullable = false)
    private Competition competition;

    @Column(name = "start_date")
    private LocalDate startDate;

    @OneToMany(mappedBy = "stage" , fetch = FetchType.EAGER)
    private List<StageResult> stageResults = new ArrayList<>();


}
