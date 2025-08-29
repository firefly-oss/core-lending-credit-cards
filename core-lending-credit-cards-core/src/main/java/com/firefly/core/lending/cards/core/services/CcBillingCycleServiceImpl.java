package com.firefly.core.lending.cards.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.mappers.CcBillingCycleMapper;
import com.firefly.core.lending.cards.interfaces.dtos.CcBillingCycleDTO;
import com.firefly.core.lending.cards.models.entities.CcBillingCycle;
import com.firefly.core.lending.cards.models.repositories.CcBillingCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Service
@Transactional
public class CcBillingCycleServiceImpl implements CcBillingCycleService {

    @Autowired
    private CcBillingCycleRepository repository;

    @Autowired
    private CcBillingCycleMapper mapper;

    @Override
    public Mono<PaginationResponse<CcBillingCycleDTO>> findAll(Long ccRevolvingLineId, FilterRequest<CcBillingCycleDTO> filterRequest) {
        filterRequest.getFilters().setCcRevolvingLineId(ccRevolvingLineId);
        return FilterUtils.createFilter(
                CcBillingCycle.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcBillingCycleDTO> create(Long ccRevolvingLineId, CcBillingCycleDTO dto) {
        dto.setCcRevolvingLineId(ccRevolvingLineId);
        CcBillingCycle entity = mapper.toEntity(dto);
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcBillingCycleDTO> getById(Long ccRevolvingLineId, Long ccBillingCycleId) {
        return Mono.from(repository.findById(ccBillingCycleId))
                .filter(entity -> entity.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcBillingCycleDTO> update(Long ccRevolvingLineId, Long ccBillingCycleId, CcBillingCycleDTO dto) {
        return Mono.from(repository.findById(ccBillingCycleId))
                .filter(entity -> entity.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(existingEntity -> {
                    existingEntity.setCycleStartDate(dto.getCycleStartDate());
                    existingEntity.setCycleEndDate(dto.getCycleEndDate());
                    existingEntity.setCycleOpenBalance(dto.getCycleOpenBalance());
                    existingEntity.setCycleCloseBalance(dto.getCycleCloseBalance());
                    existingEntity.setUpdatedAt(dto.getUpdatedAt());
                    return Mono.from(repository.save(existingEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long ccRevolvingLineId, Long ccBillingCycleId) {
        return Mono.from(repository.findById(ccBillingCycleId))
                .filter(entity -> entity.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(existingEntity -> Mono.<Void>create(MonoSink::success).then(repository.delete(existingEntity)));
    }
}
