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
import com.firefly.core.lending.cards.interfaces.dtos.CcServicingAgreementDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CcServicingAgreementService {

    /**
     * Retrieves a paginated list of servicing agreements associated with a specific revolving line,
     * based on the provided filter criteria.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the servicing agreements need to be retrieved
     * @param filterRequest     the filter criteria and pagination details used to query
     *                          the servicing agreements
     * @return a reactive Mono emitting a paginated response containing the list of
     *         CcServicingAgreementDTO objects that match the given criteria
     */
    Mono<PaginationResponse<CcServicingAgreementDTO>> findAll(UUID ccRevolvingLineId,
                                                               FilterRequest<CcServicingAgreementDTO> filterRequest);

    /**
     * Creates a new servicing agreement associated with a specific revolving line,
     * using the provided agreement data.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          for which the servicing agreement is being created
     * @param dto the {@code CcServicingAgreementDTO} object containing the details of the agreement
     *            to be created
     * @return a {@code Mono<CcServicingAgreementDTO>} emitting the created agreement DTO upon successful creation
     */
    Mono<CcServicingAgreementDTO> create(UUID ccRevolvingLineId, CcServicingAgreementDTO dto);

    /**
     * Retrieves a specific servicing agreement associated with the given revolving line ID and agreement ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          to which the servicing agreement belongs
     * @param ccServicingAgreementId the unique identifier of the servicing agreement to be retrieved
     * @return a {@code Mono<CcServicingAgreementDTO>} emitting the details of the agreement upon successful retrieval
     *         or an empty {@code Mono} if the agreement is not found
     */
    Mono<CcServicingAgreementDTO> getById(UUID ccRevolvingLineId, UUID ccServicingAgreementId);

    /**
     * Updates an existing servicing agreement associated with the specified revolving line
     * and agreement ID. The agreement details are updated with the information provided in
     * the {@code CcServicingAgreementDTO} object.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          to which the servicing agreement belongs
     * @param ccServicingAgreementId the unique identifier of the agreement to be updated
     * @param dto               the {@code CcServicingAgreementDTO} object containing the updated
     *                          details of the servicing agreement
     * @return a {@code Mono<CcServicingAgreementDTO>} emitting the updated servicing agreement details
     *         upon successful completion
     */
    Mono<CcServicingAgreementDTO> update(UUID ccRevolvingLineId, UUID ccServicingAgreementId, CcServicingAgreementDTO dto);

    /**
     * Deletes a specific servicing agreement identified by the provided revolving line ID
     * and agreement ID.
     *
     * @param ccRevolvingLineId the unique identifier of the credit card revolving line
     *                          associated with the servicing agreement to be deleted
     * @param ccServicingAgreementId the unique identifier of the agreement to be deleted
     * @return a {@code Mono<Void>} that represents the completion of the delete operation
     */
    Mono<Void> delete(UUID ccRevolvingLineId, UUID ccServicingAgreementId);
}

