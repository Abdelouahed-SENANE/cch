package ma.youcode.cch.mapper;


import ma.youcode.cch.daos.interfaces.TeamDao;
import ma.youcode.cch.dtos.team.CreateTeamDTO;
import ma.youcode.cch.dtos.team.EmbeddedTeamDTO;
import ma.youcode.cch.dtos.team.TeamResponseDTO;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.TeamService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TeamMapper {

//    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamResponseDTO toResponseDTO(Team team);
    EmbeddedTeamDTO toEmbeddedDTO(Team team);
    CreateTeamDTO toCreateDTO(Team team);
    Team toTeamEntity(CreateTeamDTO dto);

}
