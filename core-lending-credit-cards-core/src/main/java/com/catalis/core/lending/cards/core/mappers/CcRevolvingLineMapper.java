package com.catalis.core.lending.cards.core.mappers;

import com.catalis.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import com.catalis.core.lending.cards.models.entities.CcRevolvingLine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CcRevolvingLineMapper {
    CcRevolvingLineDTO toDTO(CcRevolvingLine entity);
    CcRevolvingLine toEntity(CcRevolvingLineDTO dto);
}
