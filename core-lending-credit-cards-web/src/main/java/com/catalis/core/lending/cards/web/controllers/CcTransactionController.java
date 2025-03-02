package com.catalis.core.lending.cards.web.controllers;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.cards.core.services.CcTransactionService;
import com.catalis.core.lending.cards.interfaces.dtos.CcTransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines/{ccRevolvingLineId}/transactions")
@Tag(name = "CcTransaction", description = "Transaction records (purchases, fees, etc.) for a revolve line.")
@RequiredArgsConstructor
public class CcTransactionController {

    private final CcTransactionService service;

    @GetMapping
    @Operation(summary = "List or search transactions for a revolve line")
    public Mono<ResponseEntity<PaginationResponse<CcTransactionDTO>>> findAll(
            @PathVariable Long ccRevolvingLineId,
            @ModelAttribute FilterRequest<CcTransactionDTO> filterRequest) {

        return service.findAll(ccRevolvingLineId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new transaction record")
    public Mono<ResponseEntity<CcTransactionDTO>> create(
            @PathVariable Long ccRevolvingLineId,
            @RequestBody CcTransactionDTO dto) {

        return service.create(ccRevolvingLineId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccTransactionId}")
    @Operation(summary = "Get a transaction record by ID")
    public Mono<ResponseEntity<CcTransactionDTO>> getById(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccTransactionId) {

        return service.getById(ccRevolvingLineId, ccTransactionId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccTransactionId}")
    @Operation(summary = "Update a transaction record")
    public Mono<ResponseEntity<CcTransactionDTO>> update(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccTransactionId,
            @RequestBody CcTransactionDTO dto) {

        return service.update(ccRevolvingLineId, ccTransactionId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccTransactionId}")
    @Operation(summary = "Delete a transaction record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccTransactionId) {

        return service.delete(ccRevolvingLineId, ccTransactionId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}