package ma.youcode.cch.dtos.generalResult;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.embedded.GeneralResultId;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
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
