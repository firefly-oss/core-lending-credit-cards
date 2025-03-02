package com.catalis.core.lending.cards.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.cards.core.mappers.CcPaymentMapper;
import com.catalis.core.lending.cards.interfaces.dtos.CcPaymentDTO;
import com.catalis.core.lending.cards.models.entities.CcPayment;
import com.catalis.core.lending.cards.models.repositories.CcPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class CcPaymentServiceImpl implements CcPaymentService {

    @Autowired
    private CcPaymentRepository repository;

    @Autowired
    private CcPaymentMapper mapper;

    @Override
    public Mono<PaginationResponse<CcPaymentDTO>> findAll(Long ccRevolvingLineId, FilterRequest<CcPaymentDTO> filterRequest) {
        filterRequest.getFilters().setCcRevolvingLineId(ccRevolvingLineId);
        return FilterUtils.createFilter(
                CcPayment.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcPaymentDTO> create(Long ccRevolvingLineId, CcPaymentDTO dto) {
        dto.setCcRevolvingLineId(ccRevolvingLineId);
        return Mono.just(dto)
                .map(mapper::toEntity)
                .map(payment -> {
                    payment.setCreatedAt(payment.getCreatedAt() == null ? LocalDateTime.now() : payment.getCreatedAt());
                    payment.setUpdatedAt(LocalDateTime.now());
                    return payment;
                })
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcPaymentDTO> getById(Long ccRevolvingLineId, Long ccPaymentId) {
        return repository.findById(ccPaymentId)
                .filter(payment -> payment.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcPaymentDTO> update(Long ccRevolvingLineId, Long ccPaymentId, CcPaymentDTO dto) {
        return repository.findById(ccPaymentId)
                .filter(payment -> payment.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(existing -> {
                    CcPayment updated = mapper.toEntity(dto);
                    updated.setCcPaymentId(existing.getCcPaymentId());
                    updated.setCreatedAt(existing.getCreatedAt());
                    updated.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updated);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long ccRevolvingLineId, Long ccPaymentId) {
        return repository.findById(ccPaymentId)
                .filter(payment -> payment.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(repository::delete);
    }
}