package ma.youcode.cch.mapper;


import ma.youcode.cch.dtos.cyclist.CreateCyclistDTO;
import ma.youcode.cch.dtos.cyclist.CyclistResponseDTO;
import ma.youcode.cch.dtos.cyclist.EmbeddedCyclistDTO;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CyclistMapper {
    CyclistMapper INSTANCE = Mappers.getMapper(CyclistMapper.class);

    CyclistResponseDTO toResponseDTO(Cyclist entity);
    EmbeddedCyclistDTO toEmbeddedDTO(Cyclist entity);
    CreateCyclistDTO toCreateDTO(Cyclist entity);
    Cyclist toCyclistEntity(CreateCyclistDTO dto);
}
