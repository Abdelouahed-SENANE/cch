package ma.youcode.cch.DTOs.stage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStageDTO {


    private UUID stageId;

    private String startLocation;

    private String endLocation;

    private boolean completed;

    private String stageType;

    private UUID competitionId;

    private LocalDate startDate;

}
