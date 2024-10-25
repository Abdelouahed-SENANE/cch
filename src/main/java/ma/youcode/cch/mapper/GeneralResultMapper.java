package ma.youcode.cch.mapper;


import ma.youcode.cch.dtos.generalResult.CreateGeneralResultDTO;
import ma.youcode.cch.dtos.generalResult.EmbeddedGeneralResultDTO;
import ma.youcode.cch.dtos.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

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
