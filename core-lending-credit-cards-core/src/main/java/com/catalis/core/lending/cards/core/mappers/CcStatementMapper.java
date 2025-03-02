package com.catalis.core.lending.cards.core.mappers;

import com.catalis.core.lending.cards.interfaces.dtos.CcStatementDTO;
import com.catalis.core.lending.cards.models.entities.CcStatement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CcStatementMapper {
    CcStatementDTO toDTO(CcStatement entity);
    CcStatement toEntity(CcStatementDTO dto);
}
