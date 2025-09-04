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
import com.firefly.core.lending.cards.interfaces.dtos.CcTransactionDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CcTransactionService {

    /**
     * Retrieves a paginated list of credit card transactions associated with a specific
     * credit card revolving line, based on the provided filter criteria.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the transactions need to be retrieved
     * @param filterRequest     the filter criteria and pagination details used to query
     *                          the credit card transactions
     * @return a reactive Mono emitting a paginated response containing the list of
     *         CcTransactionDTO objects that match the given criteria
     */
    Mono<PaginationResponse<CcTransactionDTO>> findAll(UUID ccRevolvingLineId,
                                                       FilterRequest<CcTransactionDTO> filterRequest);

    /**
     * Creates a new credit card transaction associated with the specified revolving line
     * using the provided transaction details.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the transaction is being created
     * @param dto               the {@code CcTransactionDTO} object containing the details
     *                          of the transaction to be created
     * @return a {@code Mono<CcTransactionDTO>} emitting the created transaction details
     *         upon successful creation
     */
    Mono<CcTransactionDTO> create(UUID ccRevolvingLineId, CcTransactionDTO dto);

    /**
     * Retrieves a specific credit card transaction associated with the given
     * revolving line ID and transaction ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          to which the transaction belongs
     * @param ccTransactionId   the unique identifier of the transaction to be retrieved
     * @return a {@code Mono<CcTransactionDTO>} emitting the details of the transaction
     *         upon successful retrieval or an empty {@code Mono} if the transaction is not found
     */
    Mono<CcTransactionDTO> getById(UUID ccRevolvingLineId, UUID ccTransactionId);

    /**
     * Updates an existing credit card transaction associated with the specified
     * revolving line and transaction ID, using the details provided in the {@code CcTransactionDTO} object.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line associated
     *                          with the transaction to be updated
     * @param ccTransactionId the unique identifier of the credit card transaction to be updated
     * @param dto the {@code CcTransactionDTO} object containing the updated details of the credit card transaction
     * @return a {@code Mono<CcTransactionDTO>} emitting the updated transaction details upon successful completion
     */
    Mono<CcTransactionDTO> update(UUID ccRevolvingLineId, UUID ccTransactionId, CcTransactionDTO dto);

    /**
     * Deletes a specific credit card transaction identified by the provided revolving line ID
     * and transaction ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          associated with the transaction to be deleted
     * @param ccTransactionId   the unique identifier of the transaction to be deleted
     * @return a {@code Mono<Void>} that represents the completion of the delete operation
     */
    Mono<Void> delete(UUID ccRevolvingLineId, UUID ccTransactionId);
}
