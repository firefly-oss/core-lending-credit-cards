package com.firefly.core.lending.cards.web.controllers;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.core.services.CcRevolvingLineService;
import com.firefly.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import com.firefly.core.lending.cards.interfaces.validation.groups.CreateValidation;
import com.firefly.core.lending.cards.interfaces.validation.groups.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cc-revolving-lines")
@Tag(name = "CcRevolvingLine", description = "Operations on credit card revolving lines.")
@RequiredArgsConstructor
@Validated
public class CcRevolvingLineController {

    private final CcRevolvingLineService service;

    @GetMapping
    @Operation(summary = "List or search revolving lines")
    public Mono<ResponseEntity<PaginationResponse<CcRevolvingLineDTO>>> findAll(
            @ModelAttribute FilterRequest<CcRevolvingLineDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new revolving line")
    public Mono<ResponseEntity<CcRevolvingLineDTO>> create(
            @Valid @RequestBody CcRevolvingLineDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{ccRevolvingLineId}")
    @Operation(summary = "Get a revolving line by ID")
    public Mono<ResponseEntity<CcRevolvingLineDTO>> getById(
            @PathVariable @NotNull UUID ccRevolvingLineId) {
        return service.getById(ccRevolvingLineId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{ccRevolvingLineId}")
    @Operation(summary = "Update a revolving line")
    public Mono<ResponseEntity<CcRevolvingLineDTO>> update(
            @PathVariable @NotNull UUID ccRevolvingLineId,
            @Valid @RequestBody CcRevolvingLineDTO dto) {

        return service.update(ccRevolvingLineId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{ccRevolvingLineId}")
    @Operation(summary = "Delete a revolving line")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable @NotNull UUID ccRevolvingLineId) {
        return service.delete(ccRevolvingLineId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
