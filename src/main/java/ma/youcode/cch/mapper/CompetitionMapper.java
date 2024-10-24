package ma.youcode.cch.mapper;


import ma.youcode.cch.dtos.competition.CreateCompetitionDTO;
import ma.youcode.cch.dtos.competition.EmbeddedCompetitionDTO;
import ma.youcode.cch.dtos.competition.CompetitionResponseDTO;
import ma.youcode.cch.entity.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

//    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    CompetitionResponseDTO toResponseDTO(Competition competition);
    EmbeddedCompetitionDTO toEmbeddedDTO(Competition competition);
    CreateCompetitionDTO toCreateDTO(Competition competition);
    Competition toCompetitionEntity(CreateCompetitionDTO dto);

}