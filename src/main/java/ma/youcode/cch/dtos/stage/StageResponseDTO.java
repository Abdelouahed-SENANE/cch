package ma.youcode.cch.dtos.stage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.dtos.competition.CompetitionResponseDTO;
import ma.youcode.cch.dtos.competition.EmbeddedCompetitionDTO;
import ma.youcode.cch.entity.Competition;

import java.time.LocalDate;
import java.time.Year;
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
