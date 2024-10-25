package ma.youcode.cch.dtos.cyclist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.dtos.team.EmbeddedTeamDTO;

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
