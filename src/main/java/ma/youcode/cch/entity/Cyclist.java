package ma.youcode.cch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cyclists")
@Setter
@Getter
public class Cyclist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cyclistId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String nationality;

    @NotBlank
    private String age;

    @ManyToOne
    @JoinColumn(name = "team_id" , nullable = false)
    private Team team;

    @OneToMany(mappedBy = "cyclist" , fetch = FetchType.LAZY)
    private Set<GeneralResult> generalResults;

    @OneToMany(mappedBy = "cyclist" , fetch = FetchType.LAZY)
    private Set<Result> results;

    public Cyclist(){}


}
