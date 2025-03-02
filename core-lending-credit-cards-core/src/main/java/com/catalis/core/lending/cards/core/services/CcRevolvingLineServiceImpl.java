package com.catalis.core.lending.cards.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.cards.core.mappers.CcRevolvingLineMapper;
import com.catalis.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import com.catalis.core.lending.cards.models.entities.CcRevolvingLine;
import com.catalis.core.lending.cards.models.repositories.CcRevolvingLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CcRevolvingLineServiceImpl implements CcRevolvingLineService {

    @Autowired
    private CcRevolvingLineRepository repository;

    @Autowired
    private CcRevolvingLineMapper mapper;

    @Override
    public Mono<PaginationResponse<CcRevolvingLineDTO>> findAll(FilterRequest<CcRevolvingLineDTO> filterRequest) {
        return FilterUtils.createFilter(
                CcRevolvingLine.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcRevolvingLineDTO> create(CcRevolvingLineDTO dto) {
        CcRevolvingLine entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcRevolvingLineDTO> getById(Long ccRevolvingLineId) {
        return repository.findById(ccRevolvingLineId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("CcRevolvingLine not found")));
    }

    @Override
    public Mono<CcRevolvingLineDTO> update(Long ccRevolvingLineId, CcRevolvingLineDTO dto) {
        return repository.findById(ccRevolvingLineId)
                .switchIfEmpty(Mono.error(new RuntimeException("CcRevolvingLine not found")))
                .flatMap(existing -> {
                    CcRevolvingLine updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCcRevolvingLineId(ccRevolvingLineId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long ccRevolvingLineId) {
        return repository.findById(ccRevolvingLineId)
                .switchIfEmpty(Mono.error(new RuntimeException("CcRevolvingLine not found")))
                .flatMap(existing -> repository.deleteById(ccRevolvingLineId));
    }
}
