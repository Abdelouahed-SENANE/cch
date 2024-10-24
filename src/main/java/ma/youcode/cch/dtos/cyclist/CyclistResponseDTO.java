package ma.youcode.cch.dtos.cyclist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ma.youcode.cch.dtos.team.EmbeddedTeamDTO;
import ma.youcode.cch.dtos.team.TeamResponseDTO;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CyclistResponseDTO {

    private UUID cyclistId;

    private String firstName;

    private String lastName;

    private String nationality;

    private String age;

    private EmbeddedTeamDTO team;

}
