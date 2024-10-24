package ma.youcode.cch.dtos.competition;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
