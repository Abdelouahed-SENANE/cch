package ma.youcode.cch.mapper;


import ma.youcode.cch.DTOs.stageResult.CreateStageResultDTO;
import ma.youcode.cch.DTOs.stageResult.EmbeddedStageResultDTO;
import ma.youcode.cch.DTOs.stageResult.StageResultResponseDTO;
import ma.youcode.cch.entity.StageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StageResultMapper {

    StageResultResponseDTO toResponseDTO(StageResult stageResult);
    EmbeddedStageResultDTO toEmbeddedDTO(StageResult stageResult);
    CreateStageResultDTO toCreateDTO(StageResult stageResult);

    @Mapping(source = "stageId" , target = "stage.stageId")
    @Mapping(source = "cyclistId" , target = "cyclist.cyclistId" )
    @Mapping(source = "stageId" , target = "stageResultId.stageId")
    @Mapping(source = "cyclistId" , target = "stageResultId.cyclistId" )
    StageResult toStageResultEntity(CreateStageResultDTO dto);




}
