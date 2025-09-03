package com.firefly.core.lending.cards.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CcRevolvingLineService {

    /**
     * Retrieves a paginated list of Credit Card Revolving Lines based on the provided filter criteria.
     *
     * @param filterRequest the filter criteria and pagination details for querying
     *                      Credit Card Revolving Lines.
     * @return a reactive Mono containing the paginated response of
     *         Credit Card Revolving Line DTOs.
     */
    Mono<PaginationResponse<CcRevolvingLineDTO>> findAll(FilterRequest<CcRevolvingLineDTO> filterRequest);

    /**
     * Creates a new CcRevolvingLineDTO resource.
     *
     * @param dto the CcRevolvingLineDTO object containing the details to be created
     * @return a Mono emitting the created CcRevolvingLineDTO object
     */
    Mono<CcRevolvingLineDTO> create(CcRevolvingLineDTO dto);

    /**
     * Retrieves a CcRevolvingLineDTO by the given ID.
     *
     * @param ccRevolvingLineId the ID of the revolving line to be retrieved
     * @return a Mono emitting the CcRevolvingLineDTO if found, or an empty Mono if not found
     */
    Mono<CcRevolvingLineDTO> getById(UUID ccRevolvingLineId);

    /**
     * Updates the details of an existing credit card revolving line with the provided information.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line to be updated
     * @param dto an object containing the updated information for the credit card revolving line
     * @return a {@code Mono} that emits the updated {@code CcRevolvingLineDTO} upon successful completion
     */
    Mono<CcRevolvingLineDTO> update(UUID ccRevolvingLineId, CcRevolvingLineDTO dto);

    /**
     * Deletes the credit card revolving line identified by the provided ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line to be deleted
     * @return a {@code Mono<Void>} representing the completion of the delete operation
     */
    Mono<Void> delete(UUID ccRevolvingLineId);

}