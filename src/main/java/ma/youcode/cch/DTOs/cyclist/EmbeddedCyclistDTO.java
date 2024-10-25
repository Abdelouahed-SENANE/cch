package ma.youcode.cch.DTOs.cyclist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.DTOs.team.EmbeddedTeamDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedCyclistDTO {
    private String firstName;

    private String lastName;

    private String nationality;

    private String age;

    private EmbeddedTeamDTO team;
}
