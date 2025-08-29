package com.firefly.core.lending.cards.web.controllers;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.services.CcBillingCycleService;
import com.firefly.core.lending.cards.interfaces.dtos.CcBillingCycleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines/{ccRevolvingLineId}/billing-cycles")
@Tag(name = "CcBillingCycle", description = "Billing cycle records for a credit card revolve line.")
@RequiredArgsConstructor
public class CcBillingCycleController {

    private final CcBillingCycleService service;

    @GetMapping
    @Operation(summary = "List or search billing cycles for a revolve line")
    public Mono<ResponseEntity<PaginationResponse<CcBillingCycleDTO>>> findAll(
            @PathVariable Long ccRevolvingLineId,
            @ModelAttribute FilterRequest<CcBillingCycleDTO> filterRequest) {

        return service.findAll(ccRevolvingLineId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new billing cycle record")
    public Mono<ResponseEntity<CcBillingCycleDTO>> create(
            @PathVariable Long ccRevolvingLineId,
            @RequestBody CcBillingCycleDTO dto) {

        return service.create(ccRevolvingLineId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccBillingCycleId}")
    @Operation(summary = "Get a billing cycle by ID")
    public Mono<ResponseEntity<CcBillingCycleDTO>> getById(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId) {

        return service.getById(ccRevolvingLineId, ccBillingCycleId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccBillingCycleId}")
    @Operation(summary = "Update a billing cycle record")
    public Mono<ResponseEntity<CcBillingCycleDTO>> update(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId,
            @RequestBody CcBillingCycleDTO dto) {

        return service.update(ccRevolvingLineId, ccBillingCycleId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccBillingCycleId}")
    @Operation(summary = "Delete a billing cycle record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long ccRevolvingLineId,
            @PathVariable Long ccBillingCycleId) {

        return service.delete(ccRevolvingLineId, ccBillingCycleId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}