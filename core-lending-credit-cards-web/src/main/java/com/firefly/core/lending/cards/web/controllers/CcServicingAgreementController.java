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


package com.firefly.core.lending.cards.web.controllers;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.services.CcServicingAgreementService;
import com.firefly.core.lending.cards.interfaces.dtos.CcServicingAgreementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines/{ccRevolvingLineId}/servicing-agreements")
@Tag(name = "CcServicingAgreement", description = "Servicing agreements linking credit card revolving lines with loan servicing cases")
@RequiredArgsConstructor
public class CcServicingAgreementController {

    private final CcServicingAgreementService service;

    @GetMapping
    @Operation(summary = "List or search servicing agreements for a revolving line")
    public Mono<ResponseEntity<PaginationResponse<CcServicingAgreementDTO>>> findAll(
            @PathVariable UUID ccRevolvingLineId,
            @ModelAttribute FilterRequest<CcServicingAgreementDTO> filterRequest) {

        return service.findAll(ccRevolvingLineId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new servicing agreement")
    public Mono<ResponseEntity<CcServicingAgreementDTO>> create(
            @PathVariable UUID ccRevolvingLineId,
            @RequestBody CcServicingAgreementDTO dto) {

        return service.create(ccRevolvingLineId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccServicingAgreementId}")
    @Operation(summary = "Get a servicing agreement by ID")
    public Mono<ResponseEntity<CcServicingAgreementDTO>> getById(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccServicingAgreementId) {

        return service.getById(ccRevolvingLineId, ccServicingAgreementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccServicingAgreementId}")
    @Operation(summary = "Update a servicing agreement")
    public Mono<ResponseEntity<CcServicingAgreementDTO>> update(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccServicingAgreementId,
            @RequestBody CcServicingAgreementDTO dto) {

        return service.update(ccRevolvingLineId, ccServicingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccServicingAgreementId}")
    @Operation(summary = "Delete a servicing agreement")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccServicingAgreementId) {

        return service.delete(ccRevolvingLineId, ccServicingAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}

