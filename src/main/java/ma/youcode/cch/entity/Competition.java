package ma.youcode.cch.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

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
    private UUID competitionId;

    @NotEmpty
    private String competitionName;

    @NotEmpty
    private Year year;

    @NotEmpty
    private LocalDateTime startDate;

    @NotEmpty
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "competition")
    private Set<GeneralResult> generalResults;

    public Competition(){}

}
