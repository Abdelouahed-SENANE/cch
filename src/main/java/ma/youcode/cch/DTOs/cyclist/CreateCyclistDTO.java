package ma.youcode.cch.DTOs.cyclist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCyclistDTO {


    private UUID cyclistId;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String nationality;

    @NotEmpty
    private String age;

    @NotNull(message = "Team id must be not null")
    private UUID teamId;


}
