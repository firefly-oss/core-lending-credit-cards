package com.firefly.core.lending.cards.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.interfaces.dtos.CcBillingCycleDTO;
import reactor.core.publisher.Mono;

public interface CcBillingCycleService {

    /**
     * Retrieves a paginated list of credit card billing cycles associated with a specific
     * revolving line, based on the provided filter criteria.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the billing cycles are to be retrieved
     * @param filterRequest     the filter criteria and pagination details to query
     *                          the billing cycles
     * @return a reactive Mono containing the paginated response of
     *         CcBillingCycleDTO objects matching the criteria
     */
    Mono<PaginationResponse<CcBillingCycleDTO>> findAll(Long ccRevolvingLineId,
                                                        FilterRequest<CcBillingCycleDTO> filterRequest);

    /**
     * Creates a new billing cycle for the specified credit card revolving line.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the billing cycle is being created
     * @param dto the {@code CcBillingCycleDTO} object containing the details
     *            of the billing cycle to be created
     * @return a {@code Mono<CcBillingCycleDTO>} emitting the created billing cycle DTO
     *         upon successful completion
     */
    Mono<CcBillingCycleDTO> create(Long ccRevolvingLineId, CcBillingCycleDTO dto);

    /**
     * Retrieves a specific billing cycle associated with a given revolving line and billing cycle ID.
     *
     * @param ccRevolvingLineId the ID of the revolving line associated with the billing cycle
     * @param ccBillingCycleId the ID of the billing cycle to be retrieved
     * @return a Mono emitting the CcBillingCycleDTO if found, or an empty Mono if not found
     */
    Mono<CcBillingCycleDTO> getById(Long ccRevolvingLineId, Long ccBillingCycleId);

    /**
     * Updates an existing billing cycle identified by the provided IDs with new information.
     *
     * @param ccRevolvingLineId the ID of the credit card revolving line associated with the billing cycle
     * @param ccBillingCycleId the ID of the billing cycle to be updated
     * @param dto the {@code CcBillingCycleDTO} object containing the updated details of the billing cycle
     * @return a {@code Mono<CcBillingCycleDTO>} emitting the updated billing cycle details upon successful completion
     */
    Mono<CcBillingCycleDTO> update(Long ccRevolvingLineId, Long ccBillingCycleId, CcBillingCycleDTO dto);

    /**
     * Deletes a Credit Card Billing Cycle identified by the provided Credit Card Revolving Line ID and
     * Credit Card Billing Cycle ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line to which the
     *                          billing cycle belongs
     * @param ccBillingCycleId  the unique identifier of the billing cycle to be deleted
     * @return a {@code Mono<Void>} that completes when the deletion is successful
     */
    Mono<Void> delete(Long ccRevolvingLineId, Long ccBillingCycleId);
}
