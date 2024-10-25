package ma.youcode.cch.mapper;


import ma.youcode.cch.DTOs.generalResult.CreateGeneralResultDTO;
import ma.youcode.cch.DTOs.generalResult.EmbeddedGeneralResultDTO;
import ma.youcode.cch.DTOs.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.entity.GeneralResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneralResultMapper {

    GeneralResultResponseDTO toResponseDTO(GeneralResult generalResult);
    EmbeddedGeneralResultDTO toEmbeddedDTO(GeneralResult generalResult);
    CreateGeneralResultDTO toCreateDTO(GeneralResult generalResult);

    @Mapping(source = "competitionId" , target = "competition.competitionId")
    @Mapping(source = "cyclistId" , target = "cyclist.cyclistId" )
    @Mapping(source = "competitionId" , target = "generalResultId.competitionId")
    @Mapping(source = "cyclistId" , target = "generalResultId.cyclistId" )
    GeneralResult toGeneralResultEntity(CreateGeneralResultDTO dto);




}
