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
import com.firefly.core.lending.cards.core.mappers.CcStatementMapper;
import com.firefly.core.lending.cards.interfaces.dtos.CcStatementDTO;
import com.firefly.core.lending.cards.models.entities.CcStatement;
import com.firefly.core.lending.cards.models.repositories.CcStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CcStatementServiceImpl implements CcStatementService {

    @Autowired
    private CcStatementRepository repository;

    @Autowired
    private CcStatementMapper mapper;

    @Override
    public Mono<PaginationResponse<CcStatementDTO>> findAll(UUID ccRevolvingLineId, UUID ccBillingCycleId, FilterRequest<CcStatementDTO> filterRequest) {
        filterRequest.getFilters().setCcBillingCycleId(ccBillingCycleId);
        return FilterUtils.createFilter(
                CcStatement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcStatementDTO> create(UUID ccRevolvingLineId, UUID ccBillingCycleId, CcStatementDTO dto) {
        dto.setCcBillingCycleId(ccBillingCycleId);
        CcStatement entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcStatementDTO> getById(UUID ccRevolvingLineId, UUID ccBillingCycleId, UUID ccStatementId) {
        return repository.findById(ccStatementId)
                .filter(entity -> ccBillingCycleId.equals(entity.getCcBillingCycleId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcStatementDTO> update(UUID ccRevolvingLineId, UUID ccBillingCycleId, UUID ccStatementId, CcStatementDTO dto) {
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
    public Mono<Void> delete(UUID ccRevolvingLineId, UUID ccBillingCycleId, UUID ccStatementId) {
        return repository.findById(ccStatementId)
                .filter(entity -> ccBillingCycleId.equals(entity.getCcBillingCycleId()))
                .flatMap(repository::delete);
    }
}
