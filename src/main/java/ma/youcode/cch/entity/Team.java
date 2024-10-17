package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "team_id")
    private UUID teamId;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(mappedBy = "team" , fetch = FetchType.EAGER)
    private Set<Cyclist> cyclists;

}
