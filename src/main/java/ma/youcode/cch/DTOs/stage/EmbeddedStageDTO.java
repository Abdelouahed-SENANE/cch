package ma.youcode.cch.DTOs.stage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedStageDTO {

    private UUID stageId;

    private String startLocation;

    private String endLocation;

    private String stageType;

    private String startDate;
}
