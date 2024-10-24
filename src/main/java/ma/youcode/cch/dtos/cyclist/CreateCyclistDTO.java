package ma.youcode.cch.dtos.cyclist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.cch.dtos.team.CreateTeamDTO;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCyclistDTO {


    private UUID cyclistId;

    private String firstName;

    private String lastName;

    private String nationality;

    private String age;

    private UUID teamId;


}
