package ma.youcode.cch.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "competetion_id")
    private UUID competitionId;

    @Column(name = "competition_name" , unique = true , nullable = false)
    private String competitionName;


    @Column(name = "competition_year", unique = true , nullable = false)
    private Year year;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "competition_place")
    private String place;

    @OneToMany(mappedBy = "competition" , fetch = FetchType.EAGER)
    private Set<GeneralResult> generalResults;

    @OneToMany(mappedBy = "competition" , fetch = FetchType.EAGER)
    Set<Stage> stages;

    public Competition(){}

}
