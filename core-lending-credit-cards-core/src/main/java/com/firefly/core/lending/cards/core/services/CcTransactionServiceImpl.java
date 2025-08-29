package com.firefly.core.lending.cards.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.mappers.CcTransactionMapper;
import com.firefly.core.lending.cards.interfaces.dtos.CcTransactionDTO;
import com.firefly.core.lending.cards.models.entities.CcTransaction;
import com.firefly.core.lending.cards.models.repositories.CcTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CcTransactionServiceImpl implements CcTransactionService {

    @Autowired
    private CcTransactionRepository repository;

    @Autowired
    private CcTransactionMapper mapper;

    @Override
    public Mono<PaginationResponse<CcTransactionDTO>> findAll(Long ccRevolvingLineId, FilterRequest<CcTransactionDTO> filterRequest) {
        filterRequest.getFilters().setCcRevolvingLineId(ccRevolvingLineId);
        return FilterUtils.createFilter(
                CcTransaction.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcTransactionDTO> create(Long ccRevolvingLineId, CcTransactionDTO dto) {
        dto.setCcRevolvingLineId(ccRevolvingLineId);
        CcTransaction entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcTransactionDTO> getById(Long ccRevolvingLineId, Long ccTransactionId) {
        return repository.findById(ccTransactionId)
                .filter(transaction -> transaction.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcTransactionDTO> update(Long ccRevolvingLineId, Long ccTransactionId, CcTransactionDTO dto) {
        return repository.findById(ccTransactionId)
                .filter(transaction -> transaction.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(existingTransaction -> {
                    CcTransaction updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCcTransactionId(existingTransaction.getCcTransactionId());
                    updatedEntity.setCcRevolvingLineId(existingTransaction.getCcRevolvingLineId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long ccRevolvingLineId, Long ccTransactionId) {
        return repository.findById(ccTransactionId)
                .filter(transaction -> transaction.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(repository::delete);
    }
}