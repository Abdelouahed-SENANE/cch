package ma.youcode.cch.mapper;


import ma.youcode.cch.dtos.stage.StageResponseDTO;
import ma.youcode.cch.dtos.stage.CreateStageDTO;
import ma.youcode.cch.dtos.stage.EmbeddedStageDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface StageMapper {


    StageResponseDTO toResponseDTO(Stage stage);
    EmbeddedStageDTO toEmbeddedDTO(Stage stage);
    CreateStageDTO toCreateDTO(Stage stage);
    @Mapping(source = "competitionId" , target = "competition" , qualifiedByName = "competitionIdToCompetition")
    Stage toStageEntity(CreateStageDTO dto);

    @Named("competitionIdToCompetition")
    default Competition mapToCompetition(UUID competitionId) {
        if (competitionId == null) {
            return null;
        }

        Competition competition = new Competition();
        competition.setCompetitionId(competitionId);
        return competition;

    }

}
