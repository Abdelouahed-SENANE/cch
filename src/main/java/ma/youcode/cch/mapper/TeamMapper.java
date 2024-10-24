package ma.youcode.cch.mapper;


import ma.youcode.cch.dtos.team.CreateTeamDTO;
import ma.youcode.cch.dtos.team.EmbeddedTeamDTO;
import ma.youcode.cch.dtos.team.TeamResponseDTO;
import ma.youcode.cch.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamResponseDTO toResponseDTO(Team team);
    EmbeddedTeamDTO toEmbeddedDTO(Team team);
    CreateTeamDTO toCreateDTO(Team team);
    Team toTeamEntity(CreateTeamDTO dto);
}
