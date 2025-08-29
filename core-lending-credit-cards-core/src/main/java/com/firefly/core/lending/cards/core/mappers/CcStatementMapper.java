package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcStatementDTO;
import com.firefly.core.lending.cards.models.entities.CcStatement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CcStatementMapper {
    CcStatementDTO toDTO(CcStatement entity);
    CcStatement toEntity(CcStatementDTO dto);
}
