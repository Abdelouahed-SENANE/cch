package ma.youcode.cch.mapper;


import ma.youcode.cch.dtos.cyclist.CreateCyclistDTO;
import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.dtos.cyclist.EmbeddedCyclistDTO;
import ma.youcode.cch.entity.Cyclist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = HelperMapper.class)
public interface CyclistMapper {

//    CyclistMapper INSTANCE = Mappers.getMapper(CyclistMapper.class);

    CyclistResponseDTO toResponseDTO(Cyclist entity);
    EmbeddedCyclistDTO toEmbeddedDTO(Cyclist entity);
    CreateCyclistDTO toCreateDTO(Cyclist entity);

    @Mapping(source = "teamId", target = "team" , qualifiedByName = "teamIdToTeam")
    Cyclist toCyclistEntity(CreateCyclistDTO dto);
}
