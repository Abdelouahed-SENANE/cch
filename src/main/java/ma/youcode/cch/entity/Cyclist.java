package ma.youcode.cch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "cyclists")
@Data
public class Cyclist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cyclistId;

    private String firstName;

    private String lastName;

    private String nationality;

    private String age;

    @ManyToOne
    @JoinColumn(name = "team_id" , nullable = false)
    private Team team;

    @OneToMany(mappedBy = "cyclist" , fetch = FetchType.EAGER)
    private Set<GeneralResult> generalResults;

    @OneToMany(mappedBy = "cyclist" , fetch = FetchType.EAGER)
    private List<StageResult> stageResults = new ArrayList<>();

    public Cyclist(){}

}
