package ma.youcode.cch.DTOs.stageResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.DTOs.cyclist.EmbeddedCyclistDTO;
import ma.youcode.cch.DTOs.stage.EmbeddedStageDTO;
import ma.youcode.cch.entity.embedded.StageResultId;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageResultResponseDTO {

    private StageResultId stageResultId;

    private EmbeddedCyclistDTO cyclist;

    private EmbeddedStageDTO stage;

    private int stageRank;

    private Duration stageDuration;

}
