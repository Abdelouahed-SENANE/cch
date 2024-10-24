package ma.youcode.cch.mapper;


import ma.youcode.cch.dtos.stage.StageResponseDTO;
import ma.youcode.cch.dtos.stage.CreateStageDTO;
import ma.youcode.cch.dtos.stage.EmbeddedStageDTO;
import ma.youcode.cch.entity.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = HelperMapper.class)
public interface StageMapper {


    StageResponseDTO toResponseDTO(Stage stage);
    EmbeddedStageDTO toEmbeddedDTO(Stage stage);
    CreateStageDTO toCreateDTO(Stage stage);
    @Mapping(source = "competitionId" , target = "competition" , qualifiedByName = "competitionIdToCompetition")
    Stage toStageEntity(CreateStageDTO dto);

}
