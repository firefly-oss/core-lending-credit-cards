package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcBillingCycleDTO;
import com.firefly.core.lending.cards.models.entities.CcBillingCycle;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:38:21+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CcBillingCycleMapperImpl implements CcBillingCycleMapper {

    @Override
    public CcBillingCycleDTO toDTO(CcBillingCycle entity) {
        if ( entity == null ) {
            return null;
        }

        CcBillingCycleDTO.CcBillingCycleDTOBuilder ccBillingCycleDTO = CcBillingCycleDTO.builder();

        ccBillingCycleDTO.ccBillingCycleId( entity.getCcBillingCycleId() );
        ccBillingCycleDTO.ccRevolvingLineId( entity.getCcRevolvingLineId() );
        ccBillingCycleDTO.cycleStartDate( entity.getCycleStartDate() );
        ccBillingCycleDTO.cycleEndDate( entity.getCycleEndDate() );
        ccBillingCycleDTO.cycleOpenBalance( entity.getCycleOpenBalance() );
        ccBillingCycleDTO.cycleCloseBalance( entity.getCycleCloseBalance() );
        ccBillingCycleDTO.createdAt( entity.getCreatedAt() );
        ccBillingCycleDTO.updatedAt( entity.getUpdatedAt() );

        return ccBillingCycleDTO.build();
    }

    @Override
    public CcBillingCycle toEntity(CcBillingCycleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CcBillingCycle.CcBillingCycleBuilder ccBillingCycle = CcBillingCycle.builder();

        ccBillingCycle.ccBillingCycleId( dto.getCcBillingCycleId() );
        ccBillingCycle.ccRevolvingLineId( dto.getCcRevolvingLineId() );
        ccBillingCycle.cycleStartDate( dto.getCycleStartDate() );
        ccBillingCycle.cycleEndDate( dto.getCycleEndDate() );
        ccBillingCycle.cycleOpenBalance( dto.getCycleOpenBalance() );
        ccBillingCycle.cycleCloseBalance( dto.getCycleCloseBalance() );
        ccBillingCycle.createdAt( dto.getCreatedAt() );
        ccBillingCycle.updatedAt( dto.getUpdatedAt() );

        return ccBillingCycle.build();
    }
}
