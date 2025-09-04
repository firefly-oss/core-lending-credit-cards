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
import com.firefly.core.lending.cards.core.services.CcTransactionService;
import com.firefly.core.lending.cards.interfaces.dtos.CcTransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines/{ccRevolvingLineId}/transactions")
@Tag(name = "CcTransaction", description = "Transaction records (purchases, fees, etc.) for a revolve line.")
@RequiredArgsConstructor
public class CcTransactionController {

    private final CcTransactionService service;

    @GetMapping
    @Operation(summary = "List or search transactions for a revolve line")
    public Mono<ResponseEntity<PaginationResponse<CcTransactionDTO>>> findAll(
            @PathVariable UUID ccRevolvingLineId,
            @ModelAttribute FilterRequest<CcTransactionDTO> filterRequest) {

        return service.findAll(ccRevolvingLineId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new transaction record")
    public Mono<ResponseEntity<CcTransactionDTO>> create(
            @PathVariable UUID ccRevolvingLineId,
            @RequestBody CcTransactionDTO dto) {

        return service.create(ccRevolvingLineId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccTransactionId}")
    @Operation(summary = "Get a transaction record by ID")
    public Mono<ResponseEntity<CcTransactionDTO>> getById(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccTransactionId) {

        return service.getById(ccRevolvingLineId, ccTransactionId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccTransactionId}")
    @Operation(summary = "Update a transaction record")
    public Mono<ResponseEntity<CcTransactionDTO>> update(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccTransactionId,
            @RequestBody CcTransactionDTO dto) {

        return service.update(ccRevolvingLineId, ccTransactionId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccTransactionId}")
    @Operation(summary = "Delete a transaction record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccTransactionId) {

        return service.delete(ccRevolvingLineId, ccTransactionId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}