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
import com.firefly.core.lending.cards.core.mappers.CcTransactionMapper;
import com.firefly.core.lending.cards.interfaces.dtos.CcTransactionDTO;
import com.firefly.core.lending.cards.models.entities.CcTransaction;
import com.firefly.core.lending.cards.models.repositories.CcTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CcTransactionServiceImpl implements CcTransactionService {

    @Autowired
    private CcTransactionRepository repository;

    @Autowired
    private CcTransactionMapper mapper;

    @Override
    public Mono<PaginationResponse<CcTransactionDTO>> findAll(UUID ccRevolvingLineId, FilterRequest<CcTransactionDTO> filterRequest) {
        filterRequest.getFilters().setCcRevolvingLineId(ccRevolvingLineId);
        return FilterUtils.createFilter(
                CcTransaction.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CcTransactionDTO> create(UUID ccRevolvingLineId, CcTransactionDTO dto) {
        dto.setCcRevolvingLineId(ccRevolvingLineId);
        CcTransaction entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcTransactionDTO> getById(UUID ccRevolvingLineId, UUID ccTransactionId) {
        return repository.findById(ccTransactionId)
                .filter(transaction -> transaction.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CcTransactionDTO> update(UUID ccRevolvingLineId, UUID ccTransactionId, CcTransactionDTO dto) {
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
    public Mono<Void> delete(UUID ccRevolvingLineId, UUID ccTransactionId) {
        return repository.findById(ccTransactionId)
                .filter(transaction -> transaction.getCcRevolvingLineId().equals(ccRevolvingLineId))
                .flatMap(repository::delete);
    }
}