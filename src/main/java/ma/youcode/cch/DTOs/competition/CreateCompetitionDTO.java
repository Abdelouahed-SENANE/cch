package ma.youcode.cch.DTOs.competition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompetitionDTO {


    private UUID competitionId;

    private String competitionName;

    private Year year;

    private LocalDate startDate;

    private LocalDate endDate;

    private String place;

    private int numberOfStage;

}
