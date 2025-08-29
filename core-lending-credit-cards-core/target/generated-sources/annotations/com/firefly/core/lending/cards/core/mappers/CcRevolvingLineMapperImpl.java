package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import com.firefly.core.lending.cards.models.entities.CcRevolvingLine;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:38:21+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CcRevolvingLineMapperImpl implements CcRevolvingLineMapper {

    @Override
    public CcRevolvingLineDTO toDTO(CcRevolvingLine entity) {
        if ( entity == null ) {
            return null;
        }

        CcRevolvingLineDTO.CcRevolvingLineDTOBuilder ccRevolvingLineDTO = CcRevolvingLineDTO.builder();

        ccRevolvingLineDTO.ccRevolvingLineId( entity.getCcRevolvingLineId() );
        ccRevolvingLineDTO.accountId( entity.getAccountId() );
        ccRevolvingLineDTO.cardId( entity.getCardId() );
        ccRevolvingLineDTO.productId( entity.getProductId() );
        ccRevolvingLineDTO.creditLimit( entity.getCreditLimit() );
        ccRevolvingLineDTO.interestRate( entity.getInterestRate() );
        ccRevolvingLineDTO.currentBalance( entity.getCurrentBalance() );
        ccRevolvingLineDTO.availableCredit( entity.getAvailableCredit() );
        ccRevolvingLineDTO.revolveStatus( entity.getRevolveStatus() );
        ccRevolvingLineDTO.remarks( entity.getRemarks() );
        ccRevolvingLineDTO.createdAt( entity.getCreatedAt() );
        ccRevolvingLineDTO.updatedAt( entity.getUpdatedAt() );

        return ccRevolvingLineDTO.build();
    }

    @Override
    public CcRevolvingLine toEntity(CcRevolvingLineDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CcRevolvingLine.CcRevolvingLineBuilder ccRevolvingLine = CcRevolvingLine.builder();

        ccRevolvingLine.ccRevolvingLineId( dto.getCcRevolvingLineId() );
        ccRevolvingLine.accountId( dto.getAccountId() );
        ccRevolvingLine.cardId( dto.getCardId() );
        ccRevolvingLine.productId( dto.getProductId() );
        ccRevolvingLine.creditLimit( dto.getCreditLimit() );
        ccRevolvingLine.interestRate( dto.getInterestRate() );
        ccRevolvingLine.currentBalance( dto.getCurrentBalance() );
        ccRevolvingLine.availableCredit( dto.getAvailableCredit() );
        ccRevolvingLine.revolveStatus( dto.getRevolveStatus() );
        ccRevolvingLine.remarks( dto.getRemarks() );
        ccRevolvingLine.createdAt( dto.getCreatedAt() );
        ccRevolvingLine.updatedAt( dto.getUpdatedAt() );

        return ccRevolvingLine.build();
    }
}
