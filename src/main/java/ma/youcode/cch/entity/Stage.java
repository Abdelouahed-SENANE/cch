package ma.youcode.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "stages")
@Getter
@Setter
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID stageId;





}
