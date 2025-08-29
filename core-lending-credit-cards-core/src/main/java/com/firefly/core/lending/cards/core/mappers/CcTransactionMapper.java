package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcTransactionDTO;
import com.firefly.core.lending.cards.models.entities.CcTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CcTransactionMapper {
    CcTransactionDTO toDTO(CcTransaction entity);
    CcTransaction toEntity(CcTransactionDTO dto);
}