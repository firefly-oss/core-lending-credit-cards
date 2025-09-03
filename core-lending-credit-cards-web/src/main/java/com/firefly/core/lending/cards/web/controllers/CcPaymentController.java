package com.firefly.core.lending.cards.web.controllers;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.services.CcPaymentService;
import com.firefly.core.lending.cards.interfaces.dtos.CcPaymentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines/{ccRevolvingLineId}/payments")
@Tag(name = "CcPayment", description = "Payment records on a revolve line (paying statements, partial payments, etc.).")
@RequiredArgsConstructor
public class CcPaymentController {

    private final CcPaymentService service;

    @GetMapping
    @Operation(summary = "List or search payments for a revolve line")
    public Mono<ResponseEntity<PaginationResponse<CcPaymentDTO>>> findAll(
            @PathVariable UUID ccRevolvingLineId,
            @ModelAttribute FilterRequest<CcPaymentDTO> filterRequest) {

        return service.findAll(ccRevolvingLineId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new payment record")
    public Mono<ResponseEntity<CcPaymentDTO>> create(
            @PathVariable UUID ccRevolvingLineId,
            @RequestBody CcPaymentDTO dto) {

        return service.create(ccRevolvingLineId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccPaymentId}")
    @Operation(summary = "Get a payment record by ID")
    public Mono<ResponseEntity<CcPaymentDTO>> getById(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccPaymentId) {

        return service.getById(ccRevolvingLineId, ccPaymentId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccPaymentId}")
    @Operation(summary = "Update a payment record")
    public Mono<ResponseEntity<CcPaymentDTO>> update(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccPaymentId,
            @RequestBody CcPaymentDTO dto) {

        return service.update(ccRevolvingLineId, ccPaymentId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccPaymentId}")
    @Operation(summary = "Delete a payment record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID ccRevolvingLineId,
            @PathVariable UUID ccPaymentId) {

        return service.delete(ccRevolvingLineId, ccPaymentId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}