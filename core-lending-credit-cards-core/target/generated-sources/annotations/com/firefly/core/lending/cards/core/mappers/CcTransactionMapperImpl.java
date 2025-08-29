package com.firefly.core.lending.cards.core.mappers;

import com.firefly.core.lending.cards.interfaces.dtos.CcTransactionDTO;
import com.firefly.core.lending.cards.models.entities.CcTransaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:18+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CcTransactionMapperImpl implements CcTransactionMapper {

    @Override
    public CcTransactionDTO toDTO(CcTransaction entity) {
        if ( entity == null ) {
            return null;
        }

        CcTransactionDTO.CcTransactionDTOBuilder ccTransactionDTO = CcTransactionDTO.builder();

        ccTransactionDTO.ccTransactionId( entity.getCcTransactionId() );
        ccTransactionDTO.ccRevolvingLineId( entity.getCcRevolvingLineId() );
        ccTransactionDTO.transactionId( entity.getTransactionId() );
        ccTransactionDTO.transactionAmount( entity.getTransactionAmount() );
        ccTransactionDTO.currencyCode( entity.getCurrencyCode() );
        ccTransactionDTO.transactionDate( entity.getTransactionDate() );
        ccTransactionDTO.transactionType( entity.getTransactionType() );
        ccTransactionDTO.transactionStatus( entity.getTransactionStatus() );
        ccTransactionDTO.note( entity.getNote() );
        ccTransactionDTO.createdAt( entity.getCreatedAt() );
        ccTransactionDTO.updatedAt( entity.getUpdatedAt() );

        return ccTransactionDTO.build();
    }

    @Override
    public CcTransaction toEntity(CcTransactionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CcTransaction.CcTransactionBuilder ccTransaction = CcTransaction.builder();

        ccTransaction.ccTransactionId( dto.getCcTransactionId() );
        ccTransaction.ccRevolvingLineId( dto.getCcRevolvingLineId() );
        ccTransaction.transactionId( dto.getTransactionId() );
        ccTransaction.transactionAmount( dto.getTransactionAmount() );
        ccTransaction.currencyCode( dto.getCurrencyCode() );
        ccTransaction.transactionDate( dto.getTransactionDate() );
        ccTransaction.transactionType( dto.getTransactionType() );
        ccTransaction.transactionStatus( dto.getTransactionStatus() );
        ccTransaction.note( dto.getNote() );
        ccTransaction.createdAt( dto.getCreatedAt() );
        ccTransaction.updatedAt( dto.getUpdatedAt() );

        return ccTransaction.build();
    }
}
