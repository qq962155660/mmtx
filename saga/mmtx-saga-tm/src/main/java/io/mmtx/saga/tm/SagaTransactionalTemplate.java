/*
 *  Copyright 1999-2019 Mmtx.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.mmtx.saga.tm;

import io.mmtx.core.exception.TransactionException;
import io.mmtx.core.model.BranchStatus;
import io.mmtx.core.model.GlobalStatus;
import io.mmtx.tm.api.GlobalTransaction;
import io.mmtx.tm.api.TransactionalExecutor;
import io.mmtx.tm.api.transaction.TransactionInfo;

/**
 * Template of executing business logic with a global transaction for SAGA mode
 *
 * @author lorne.cl
 */
public interface SagaTransactionalTemplate {

    void commitTransaction(GlobalTransaction tx) throws TransactionalExecutor.ExecutionException;

    void rollbackTransaction(GlobalTransaction tx, Throwable ex)
        throws TransactionException, TransactionalExecutor.ExecutionException;

    GlobalTransaction beginTransaction(TransactionInfo txInfo) throws TransactionalExecutor.ExecutionException;

    GlobalTransaction reloadTransaction(String xid)
        throws TransactionalExecutor.ExecutionException, TransactionException;

    void reportTransaction(GlobalTransaction tx, GlobalStatus globalStatus)
        throws TransactionalExecutor.ExecutionException;

    long branchRegister(String resourceId, String clientId, String xid, String applicationData, String lockKeys)
        throws TransactionException;

    void branchReport(String xid, long branchId, BranchStatus status, String applicationData)
        throws TransactionException;

    int getTimeout();

    void triggerAfterCompletion();

    void cleanUp();
}