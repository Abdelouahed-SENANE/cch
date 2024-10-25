package ma.youcode.cch.DTOs.generalResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGeneralResultDTO {


    private UUID cyclistId;

    private UUID competitionId;

    private int overallRank;

    private Duration overallDuration;

}
