package ma.youcode.cch.dtos.competition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedCompetitionDTO {

    private String competitionName;

    private Year year;

    private String startDate;

    private String endDate;

    private String place;


}
