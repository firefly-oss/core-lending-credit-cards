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


package com.firefly.core.lending.cards.models.repositories;

import com.firefly.core.lending.cards.models.entities.CcServicingAgreement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CcServicingAgreementRepository extends BaseRepository<CcServicingAgreement, UUID> {
    
    /**
     * Find all servicing agreements for a specific revolving line
     */
    Flux<CcServicingAgreement> findByCcRevolvingLineId(UUID ccRevolvingLineId);
    
    /**
     * Find all servicing agreements for a specific loan servicing case
     */
    Flux<CcServicingAgreement> findByLoanServicingCaseId(UUID loanServicingCaseId);
    
    /**
     * Find active servicing agreements for a specific revolving line
     */
    Flux<CcServicingAgreement> findByCcRevolvingLineIdAndIsActive(UUID ccRevolvingLineId, Boolean isActive);
    
    /**
     * Find servicing agreement by agreement number
     */
    Mono<CcServicingAgreement> findByAgreementNumber(String agreementNumber);
}

