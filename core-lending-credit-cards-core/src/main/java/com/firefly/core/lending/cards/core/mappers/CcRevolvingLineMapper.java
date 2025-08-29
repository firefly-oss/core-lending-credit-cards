package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import com.firefly.core.lending.cards.models.entities.CcRevolvingLine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CcRevolvingLineMapper {
    CcRevolvingLineDTO toDTO(CcRevolvingLine entity);
    CcRevolvingLine toEntity(CcRevolvingLineDTO dto);
}
