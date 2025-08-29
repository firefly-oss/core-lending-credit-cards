package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcPaymentDTO;
import com.firefly.core.lending.cards.models.entities.CcPayment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:18+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CcPaymentMapperImpl implements CcPaymentMapper {

    @Override
    public CcPaymentDTO toDTO(CcPayment entity) {
        if ( entity == null ) {
            return null;
        }

        CcPaymentDTO.CcPaymentDTOBuilder ccPaymentDTO = CcPaymentDTO.builder();

        ccPaymentDTO.ccPaymentId( entity.getCcPaymentId() );
        ccPaymentDTO.ccRevolvingLineId( entity.getCcRevolvingLineId() );
        ccPaymentDTO.ccStatementId( entity.getCcStatementId() );
        ccPaymentDTO.transactionId( entity.getTransactionId() );
        ccPaymentDTO.paymentAmount( entity.getPaymentAmount() );
        ccPaymentDTO.paymentDate( entity.getPaymentDate() );
        ccPaymentDTO.isPartialPayment( entity.getIsPartialPayment() );
        ccPaymentDTO.note( entity.getNote() );
        ccPaymentDTO.createdAt( entity.getCreatedAt() );
        ccPaymentDTO.updatedAt( entity.getUpdatedAt() );

        return ccPaymentDTO.build();
    }

    @Override
    public CcPayment toEntity(CcPaymentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CcPayment.CcPaymentBuilder ccPayment = CcPayment.builder();

        ccPayment.ccPaymentId( dto.getCcPaymentId() );
        ccPayment.ccRevolvingLineId( dto.getCcRevolvingLineId() );
        ccPayment.ccStatementId( dto.getCcStatementId() );
        ccPayment.transactionId( dto.getTransactionId() );
        ccPayment.paymentAmount( dto.getPaymentAmount() );
        ccPayment.paymentDate( dto.getPaymentDate() );
        ccPayment.isPartialPayment( dto.getIsPartialPayment() );
        ccPayment.note( dto.getNote() );
        ccPayment.createdAt( dto.getCreatedAt() );
        ccPayment.updatedAt( dto.getUpdatedAt() );

        return ccPayment.build();
    }
}
