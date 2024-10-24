package ma.youcode.cch.dtos.competition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionResponseDTO {

    private UUID competitionId;

    private String competitionName;

    private Year year;

    private String startDate;

    private String endDate;

    private String place;

    private int numberOfStage;


}
