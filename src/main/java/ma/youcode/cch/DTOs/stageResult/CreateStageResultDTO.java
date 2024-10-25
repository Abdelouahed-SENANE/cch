package ma.youcode.cch.DTOs.stageResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStageResultDTO {


    private UUID cyclistId;

    private UUID stageId;

    private int stageRank;

    private Duration stageDuration;

}
