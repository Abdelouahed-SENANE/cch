package ma.youcode.cch.DTOs.stage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.DTOs.competition.EmbeddedCompetitionDTO;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageResponseDTO {

    private UUID stageId;

    private String startLocation;

    private String endLocation;

    private String stageType;

    private String startDate;

    private EmbeddedCompetitionDTO competition;


}
