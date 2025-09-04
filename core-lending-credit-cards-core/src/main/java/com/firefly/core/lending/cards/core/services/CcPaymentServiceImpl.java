/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.cards.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.mappers.CcPaymentMapper;
import com.firefly.core.lending.cards.interfaces.dtos.CcPaymentDTO;
import com.firefly.core.lending.cards.models.entities.CcPayment;
import com.firefly.core.lending.cards.models.repositories.CcPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class CcPaymentServiceImpl implements CcPaymentService {

    @Autowired
    private CcPaymentRepository repository;

    @Autowired
    private CcPaymentMapper mapper;

    @Override
    public Mono<PaginationResponse<CcPaymentDTO>> findAll(UUID ccRevolvingLineId, FilterRequest<CcPaymentDTO> filterRequest) {
        filterRequest.getFilters().setCcRevolvingLineId(ccRevolvingLineId);
        return FilterUtils.createFilter(
                CcPayment.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcPaymentDTO> create(UUID ccRevolvingLineId, CcPaymentDTO dto) {
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
    public Mono<CcPaymentDTO> getById(UUID ccRevolvingLineId, UUID ccPaymentId) {
        return repository.findById(ccPaymentId)
                .filter(payment -> payment.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcPaymentDTO> update(UUID ccRevolvingLineId, UUID ccPaymentId, CcPaymentDTO dto) {
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
    public Mono<Void> delete(UUID ccRevolvingLineId, UUID ccPaymentId) {
        return repository.findById(ccPaymentId)
                .filter(payment -> payment.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(repository::delete);
    }
}