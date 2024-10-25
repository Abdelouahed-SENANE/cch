package ma.youcode.cch.DTOs.stageResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.DTOs.cyclist.EmbeddedCyclistDTO;
import ma.youcode.cch.DTOs.stage.EmbeddedStageDTO;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Stage;

import java.time.Duration;
import java.time.Year;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedStageResultDTO {

    private EmbeddedCyclistDTO cyclist;

    private EmbeddedStageDTO stage;

    private int stageRank;

    private Duration stageDuration;


}
