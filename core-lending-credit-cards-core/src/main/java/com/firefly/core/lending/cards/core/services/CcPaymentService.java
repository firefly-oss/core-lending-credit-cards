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
import com.firefly.core.lending.cards.interfaces.dtos.CcPaymentDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CcPaymentService {

    /**
     * Retrieves a paginated list of credit card payments associated with a specific revolving line,
     * based on the provided filter criteria.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the payments need to be retrieved
     * @param filterRequest     the filter criteria and pagination details used to query
     *                          the credit card payments
     * @return a reactive Mono emitting a paginated response containing the list of
     *         CcPaymentDTO objects that match the given criteria
     */
    Mono<PaginationResponse<CcPaymentDTO>> findAll(UUID ccRevolvingLineId,
                                                   FilterRequest<CcPaymentDTO> filterRequest);

    /**
     * Creates a new credit card payment associated with a specific revolving line,
     * using the provided payment data.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the payment is being created
     * @param dto the {@code CcPaymentDTO} object containing the details of the payment
     *            to be created
     * @return a {@code Mono<CcPaymentDTO>} emitting the created payment DTO upon successful creation
     */
    Mono<CcPaymentDTO> create(UUID ccRevolvingLineId, CcPaymentDTO dto);

    /**
     * Retrieves a specific credit card payment associated with the given revolving line ID and payment ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          to which the payment belongs
     * @param ccPaymentId the unique identifier of the credit card payment to be retrieved
     * @return a {@code Mono<CcPaymentDTO>} emitting the details of the payment upon successful retrieval
     *         or an empty {@code Mono} if the payment is not found
     */
    Mono<CcPaymentDTO> getById(UUID ccRevolvingLineId, UUID ccPaymentId);

    /**
     * Updates an existing credit card payment associated with the specified revolving line
     * and payment ID. The payment details are updated with the information provided in
     * the {@code CcPaymentDTO} object.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          to which the payment belongs
     * @param ccPaymentId       the unique identifier of the payment to be updated
     * @param dto               the {@code CcPaymentDTO} object containing the updated
     *                          details of the credit card payment
     * @return a {@code Mono<CcPaymentDTO>} emitting the updated credit card payment details
     *         upon successful completion
     */
    Mono<CcPaymentDTO> update(UUID ccRevolvingLineId, UUID ccPaymentId, CcPaymentDTO dto);

    /**
     * Deletes a specific credit card payment identified by the provided revolving line ID
     * and payment ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          associated with the payment to be deleted
     * @param ccPaymentId       the unique identifier of the payment to be deleted
     * @return a {@code Mono<Void>} that represents the completion of the delete operation
     */
    Mono<Void> delete(UUID ccRevolvingLineId, UUID ccPaymentId);
}
