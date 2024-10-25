package ma.youcode.cch.DTOs.generalResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.DTOs.competition.EmbeddedCompetitionDTO;
import ma.youcode.cch.DTOs.cyclist.EmbeddedCyclistDTO;
import ma.youcode.cch.entity.embedded.GeneralResultId;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResultResponseDTO {

    private GeneralResultId generalResultId;

    private EmbeddedCyclistDTO cyclist;

    private EmbeddedCompetitionDTO competition;

    private int overallRank;

    private Duration overallDuration;

}
