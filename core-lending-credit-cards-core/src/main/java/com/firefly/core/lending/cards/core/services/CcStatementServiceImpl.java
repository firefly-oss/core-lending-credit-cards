package com.firefly.core.lending.cards.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.mappers.CcStatementMapper;
import com.firefly.core.lending.cards.interfaces.dtos.CcStatementDTO;
import com.firefly.core.lending.cards.models.entities.CcStatement;
import com.firefly.core.lending.cards.models.repositories.CcStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CcStatementServiceImpl implements CcStatementService {

    @Autowired
    private CcStatementRepository repository;

    @Autowired
    private CcStatementMapper mapper;

    @Override
    public Mono<PaginationResponse<CcStatementDTO>> findAll(Long ccRevolvingLineId, Long ccBillingCycleId, FilterRequest<CcStatementDTO> filterRequest) {
        filterRequest.getFilters().setCcBillingCycleId(ccBillingCycleId);
        return FilterUtils.createFilter(
                CcStatement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcStatementDTO> create(Long ccRevolvingLineId, Long ccBillingCycleId, CcStatementDTO dto) {
        dto.setCcBillingCycleId(ccBillingCycleId);
        CcStatement entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcStatementDTO> getById(Long ccRevolvingLineId, Long ccBillingCycleId, Long ccStatementId) {
        return repository.findById(ccStatementId)
                .filter(entity -> ccBillingCycleId.equals(entity.getCcBillingCycleId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcStatementDTO> update(Long ccRevolvingLineId, Long ccBillingCycleId, Long ccStatementId, CcStatementDTO dto) {
        return repository.findById(ccStatementId)
                .filter(entity -> ccBillingCycleId.equals(entity.getCcBillingCycleId()))
                .flatMap(existingEntity -> {
                    CcStatement updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCcStatementId(existingEntity.getCcStatementId());
                    updatedEntity.setCreatedAt(existingEntity.getCreatedAt());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long ccRevolvingLineId, Long ccBillingCycleId, Long ccStatementId) {
        return repository.findById(ccStatementId)
                .filter(entity -> ccBillingCycleId.equals(entity.getCcBillingCycleId()))
                .flatMap(repository::delete);
    }
}
