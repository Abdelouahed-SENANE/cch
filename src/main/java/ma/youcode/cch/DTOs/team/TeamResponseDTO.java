package ma.youcode.cch.DTOs.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseDTO {

    private UUID teamId;
    private String teamName;


}
