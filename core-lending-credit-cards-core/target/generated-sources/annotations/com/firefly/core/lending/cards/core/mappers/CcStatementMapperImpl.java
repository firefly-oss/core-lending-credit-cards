package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcStatementDTO;
import com.firefly.core.lending.cards.models.entities.CcStatement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:38:21+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CcStatementMapperImpl implements CcStatementMapper {

    @Override
    public CcStatementDTO toDTO(CcStatement entity) {
        if ( entity == null ) {
            return null;
        }

        CcStatementDTO.CcStatementDTOBuilder ccStatementDTO = CcStatementDTO.builder();

        ccStatementDTO.ccStatementId( entity.getCcStatementId() );
        ccStatementDTO.ccBillingCycleId( entity.getCcBillingCycleId() );
        ccStatementDTO.statementDate( entity.getStatementDate() );
        ccStatementDTO.statementBalance( entity.getStatementBalance() );
        ccStatementDTO.minimumPayment( entity.getMinimumPayment() );
        ccStatementDTO.paymentDueDate( entity.getPaymentDueDate() );
        ccStatementDTO.isPaid( entity.getIsPaid() );
        ccStatementDTO.paidDate( entity.getPaidDate() );
        ccStatementDTO.note( entity.getNote() );
        ccStatementDTO.createdAt( entity.getCreatedAt() );
        ccStatementDTO.updatedAt( entity.getUpdatedAt() );

        return ccStatementDTO.build();
    }

    @Override
    public CcStatement toEntity(CcStatementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CcStatement.CcStatementBuilder ccStatement = CcStatement.builder();

        ccStatement.ccStatementId( dto.getCcStatementId() );
        ccStatement.ccBillingCycleId( dto.getCcBillingCycleId() );
        ccStatement.statementDate( dto.getStatementDate() );
        ccStatement.statementBalance( dto.getStatementBalance() );
        ccStatement.minimumPayment( dto.getMinimumPayment() );
        ccStatement.paymentDueDate( dto.getPaymentDueDate() );
        ccStatement.isPaid( dto.getIsPaid() );
        ccStatement.paidDate( dto.getPaidDate() );
        ccStatement.note( dto.getNote() );
        ccStatement.createdAt( dto.getCreatedAt() );
        ccStatement.updatedAt( dto.getUpdatedAt() );

        return ccStatement.build();
    }
}
