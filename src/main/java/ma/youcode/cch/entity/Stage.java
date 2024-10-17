package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stages")
@Getter
@Setter
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID stageId;

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "end_location")
    private String endLocation;

    private int number;

    private String type;

    @ManyToOne
    @JoinColumn(name = "competition_id" , nullable = false)
    private Competition competition;

    @Column(name = "start_date")
    private LocalDate startDate;


}
