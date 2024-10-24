package ma.youcode.cch.dtos.stage;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.entity.Competition;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStageDTO {


    private UUID stageId;

    private String startLocation;

    private String endLocation;

    private String stageType;

    private UUID competitionId;

    private LocalDate startDate;

}
