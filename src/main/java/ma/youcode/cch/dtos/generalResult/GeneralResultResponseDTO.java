package ma.youcode.cch.dtos.generalResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.dtos.competition.EmbeddedCompetitionDTO;
import ma.youcode.cch.dtos.cyclist.EmbeddedCyclistDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.embedded.GeneralResultId;

import java.time.Duration;
import java.time.Year;
import java.util.UUID;

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
