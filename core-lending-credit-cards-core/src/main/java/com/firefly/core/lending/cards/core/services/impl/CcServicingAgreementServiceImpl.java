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


package com.firefly.core.lending.cards.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.mappers.CcServicingAgreementMapper;
import com.firefly.core.lending.cards.core.services.CcServicingAgreementService;
import com.firefly.core.lending.cards.interfaces.dtos.CcServicingAgreementDTO;
import com.firefly.core.lending.cards.models.entities.CcServicingAgreement;
import com.firefly.core.lending.cards.models.repositories.CcServicingAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class CcServicingAgreementServiceImpl implements CcServicingAgreementService {

    @Autowired
    private CcServicingAgreementRepository repository;

    @Autowired
    private CcServicingAgreementMapper mapper;

    @Override
    public Mono<PaginationResponse<CcServicingAgreementDTO>> findAll(UUID ccRevolvingLineId, FilterRequest<CcServicingAgreementDTO> filterRequest) {
        filterRequest.getFilters().setCcRevolvingLineId(ccRevolvingLineId);
        return FilterUtils.createFilter(
                CcServicingAgreement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcServicingAgreementDTO> create(UUID ccRevolvingLineId, CcServicingAgreementDTO dto) {
        dto.setCcRevolvingLineId(ccRevolvingLineId);
        return Mono.just(dto)
                .map(mapper::toEntity)
                .map(agreement -> {
                    agreement.setCreatedAt(agreement.getCreatedAt() == null ? LocalDateTime.now() : agreement.getCreatedAt());
                    agreement.setUpdatedAt(LocalDateTime.now());
                    return agreement;
                })
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcServicingAgreementDTO> getById(UUID ccRevolvingLineId, UUID ccServicingAgreementId) {
        return repository.findById(ccServicingAgreementId)
                .filter(agreement -> agreement.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcServicingAgreementDTO> update(UUID ccRevolvingLineId, UUID ccServicingAgreementId, CcServicingAgreementDTO dto) {
        return repository.findById(ccServicingAgreementId)
                .filter(agreement -> agreement.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(existing -> {
                    CcServicingAgreement updated = mapper.toEntity(dto);
                    updated.setCcServicingAgreementId(existing.getCcServicingAgreementId());
                    updated.setCreatedAt(existing.getCreatedAt());
                    updated.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updated);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID ccRevolvingLineId, UUID ccServicingAgreementId) {
        return repository.findById(ccServicingAgreementId)
                .filter(agreement -> agreement.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(repository::delete);
    }
}

