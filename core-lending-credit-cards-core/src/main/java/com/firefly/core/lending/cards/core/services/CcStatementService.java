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


package com.firefly.core.lending.cards.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.cards.interfaces.dtos.CcStatementDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CcStatementService {

    /**
     * Retrieves a paginated list of credit card statements associated with a specific
     * credit card revolving line and billing cycle, based on the provided filter criteria.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the statements need to be retrieved
     * @param ccBillingCycleId  the unique identifier of the billing cycle for which the
     *                          statements need to be retrieved
     * @param filterRequest     the filter criteria and pagination details used to query
     *                          the credit card statements
     * @return a reactive Mono emitting a paginated response containing the list of
     *         CcStatementDTO objects that match the given criteria
     */
    Mono<PaginationResponse<CcStatementDTO>> findAll(UUID ccRevolvingLineId,
                                                     UUID ccBillingCycleId,
                                                     FilterRequest<CcStatementDTO> filterRequest);

    /**
     * Creates a new credit card statement associated with a specific revolving line
     * and billing cycle, using the provided statement data.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the statement is being created
     * @param ccBillingCycleId the unique identifier of the billing cycle
     *                         to which the statement belongs
     * @param dto the {@code CcStatementDTO} object containing the details of the statement
     *            to be created
     * @return a {@code Mono<CcStatementDTO>} emitting the created statement DTO
     *         upon successful creation
     */
    Mono<CcStatementDTO> create(UUID ccRevolvingLineId, UUID ccBillingCycleId, CcStatementDTO dto);

    /**
     * Retrieves a specific credit card statement associated with the given
     * revolving line ID, billing cycle ID, and statement ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          to which the statement belongs
     * @param ccBillingCycleId  the unique identifier of the billing cycle associated
     *                          with the statement
     * @param ccStatementId     the unique identifier of the statement to be retrieved
     * @return a {@code Mono<CcStatementDTO>} emitting the details of the statement
     *         upon successful retrieval or an empty {@code Mono} if the statement
     *         is not found
     */
    Mono<CcStatementDTO> getById(UUID ccRevolvingLineId, UUID ccBillingCycleId, UUID ccStatementId);

    /**
     * Updates an existing credit card statement associated with the specified revolving line,
     * billing cycle, and statement ID. The statement is updated with the details provided in the
     * {@code CcStatementDTO} object.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line associated
     *                          with the statement to be updated
     * @param ccBillingCycleId the unique identifier of the credit card billing cycle associated
     *                         with the statement to be updated
     * @param ccStatementId the unique identifier of the credit card statement to be updated
     * @param dto the {@code CcStatementDTO} object containing the updated details of the credit card statement
     * @return a {@code Mono<CcStatementDTO>} emitting the updated credit card statement details
     *         upon successful completion
     */
    Mono<CcStatementDTO> update(UUID ccRevolvingLineId, UUID ccBillingCycleId, UUID ccStatementId, CcStatementDTO dto);

    /**
     * Deletes a specific credit card statement identified by the provided revolving line ID,
     * billing cycle ID, and statement ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          associated with the statement to be deleted
     * @param ccBillingCycleId  the unique identifier of the billing cycle
     *                          associated with the statement to be deleted
     * @param ccStatementId     the unique identifier of the statement to be deleted
     * @return a {@code Mono<Void>} representing the completion of the delete operation
     */
    Mono<Void> delete(UUID ccRevolvingLineId, UUID ccBillingCycleId, UUID ccStatementId);
}
