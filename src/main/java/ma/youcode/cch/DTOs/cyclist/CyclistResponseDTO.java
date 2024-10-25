package ma.youcode.cch.DTOs.cyclist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.DTOs.team.EmbeddedTeamDTO;

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
