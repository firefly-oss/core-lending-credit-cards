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
import com.firefly.core.lending.cards.core.mappers.CcRevolvingLineMapper;
import com.firefly.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import com.firefly.core.lending.cards.models.entities.CcRevolvingLine;
import com.firefly.core.lending.cards.models.repositories.CcRevolvingLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
    public Mono<CcRevolvingLineDTO> getById(UUID ccRevolvingLineId) {
        return repository.findById(ccRevolvingLineId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("CcRevolvingLine not found")));
    }

    @Override
    public Mono<CcRevolvingLineDTO> update(UUID ccRevolvingLineId, CcRevolvingLineDTO dto) {
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
    public Mono<Void> delete(UUID ccRevolvingLineId) {
        return repository.findById(ccRevolvingLineId)
                .switchIfEmpty(Mono.error(new RuntimeException("CcRevolvingLine not found")))
                .flatMap(existing -> repository.deleteById(ccRevolvingLineId));
    }
}
