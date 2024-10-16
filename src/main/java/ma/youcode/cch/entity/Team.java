package ma.youcode.cch.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "team_id")
    private UUID teamId;

    @OneToMany(mappedBy = "team" , cascade = CascadeType.ALL)
    private Set<Cyclist> cyclists;
}
