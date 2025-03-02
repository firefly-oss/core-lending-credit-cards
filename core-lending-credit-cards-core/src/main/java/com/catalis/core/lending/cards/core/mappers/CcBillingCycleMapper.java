package com.catalis.core.lending.cards.core.mappers;

import com.catalis.core.lending.cards.interfaces.dtos.CcBillingCycleDTO;
import com.catalis.core.lending.cards.models.entities.CcBillingCycle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CcBillingCycleMapper {
    CcBillingCycleDTO toDTO(CcBillingCycle entity);
    CcBillingCycle toEntity(CcBillingCycleDTO dto);
}