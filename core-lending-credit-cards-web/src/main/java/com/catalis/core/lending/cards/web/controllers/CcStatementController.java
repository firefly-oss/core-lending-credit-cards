package com.catalis.core.lending.cards.web.controllers;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.cards.core.services.CcStatementService;
import com.catalis.core.lending.cards.interfaces.dtos.CcStatementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines/{ccRevolvingLineId}/billing-cycles/{ccBillingCycleId}/statements")
@Tag(name = "CcStatement", description = "Monthly statement records for a billing cycle.")
@RequiredArgsConstructor
public class CcStatementController {

    private final CcStatementService service;

    @GetMapping
    @Operation(summary = "List or search statements within a billing cycle")
    public Mono<ResponseEntity<PaginationResponse<CcStatementDTO>>> findAll(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId,
            @ModelAttribute FilterRequest<CcStatementDTO> filterRequest) {

        return service.findAll(ccRevolvingLineId, ccBillingCycleId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new statement")
    public Mono<ResponseEntity<CcStatementDTO>> create(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId,
            @RequestBody CcStatementDTO dto) {

        return service.create(ccRevolvingLineId, ccBillingCycleId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccStatementId}")
    @Operation(summary = "Get a statement by ID")
    public Mono<ResponseEntity<CcStatementDTO>> getById(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId,
            @PathVariable Long ccStatementId) {

        return service.getById(ccRevolvingLineId, ccBillingCycleId, ccStatementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccStatementId}")
    @Operation(summary = "Update a statement record")
    public Mono<ResponseEntity<CcStatementDTO>> update(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId,
            @PathVariable Long ccStatementId,
            @RequestBody CcStatementDTO dto) {

        return service.update(ccRevolvingLineId, ccBillingCycleId, ccStatementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccStatementId}")
    @Operation(summary = "Delete a statement record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId,
            @PathVariable Long ccStatementId) {

        return service.delete(ccRevolvingLineId, ccBillingCycleId, ccStatementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
