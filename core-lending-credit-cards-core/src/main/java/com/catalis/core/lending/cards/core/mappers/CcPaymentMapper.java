package com.catalis.core.lending.cards.core.mappers;

import com.catalis.core.lending.cards.interfaces.dtos.CcPaymentDTO;
import com.catalis.core.lending.cards.models.entities.CcPayment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CcPaymentMapper {
    CcPaymentDTO toDTO(CcPayment entity);
    CcPayment toEntity(CcPaymentDTO dto);
}
