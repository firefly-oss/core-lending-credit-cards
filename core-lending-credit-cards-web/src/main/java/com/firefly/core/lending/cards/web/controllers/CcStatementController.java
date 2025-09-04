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
import com.firefly.core.lending.cards.core.services.CcStatementService;
import com.firefly.core.lending.cards.interfaces.dtos.CcStatementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines/{ccRevolvingLineId}/billing-cycles/{ccBillingCycleId}/statements")
@Tag(name = "CcStatement", description = "Monthly statement records for a billing cycle.")
@RequiredArgsConstructor
public class CcStatementController {

    private final CcStatementService service;

    @GetMapping
    @Operation(summary = "List or search statements within a billing cycle")
    public Mono<ResponseEntity<PaginationResponse<CcStatementDTO>>> findAll(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccBillingCycleId,
            @ModelAttribute FilterRequest<CcStatementDTO> filterRequest) {

        return service.findAll(ccRevolvingLineId, ccBillingCycleId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new statement")
    public Mono<ResponseEntity<CcStatementDTO>> create(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccBillingCycleId,
            @RequestBody CcStatementDTO dto) {

        return service.create(ccRevolvingLineId, ccBillingCycleId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccStatementId}")
    @Operation(summary = "Get a statement by ID")
    public Mono<ResponseEntity<CcStatementDTO>> getById(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccBillingCycleId,
            @PathVariable UUID ccStatementId) {

        return service.getById(ccRevolvingLineId, ccBillingCycleId, ccStatementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccStatementId}")
    @Operation(summary = "Update a statement record")
    public Mono<ResponseEntity<CcStatementDTO>> update(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccBillingCycleId,
            @PathVariable UUID ccStatementId,
            @RequestBody CcStatementDTO dto) {

        return service.update(ccRevolvingLineId, ccBillingCycleId, ccStatementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccStatementId}")
    @Operation(summary = "Delete a statement record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccBillingCycleId,
            @PathVariable UUID ccStatementId) {

        return service.delete(ccRevolvingLineId, ccBillingCycleId, ccStatementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
