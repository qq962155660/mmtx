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
package io.mmtx.saga.engine.mock;

import io.mmtx.core.exception.TransactionException;
import io.mmtx.core.model.BranchStatus;
import io.mmtx.core.model.GlobalStatus;
import io.mmtx.saga.tm.SagaTransactionalTemplate;
import io.mmtx.server.UUIDGenerator;
import io.mmtx.tm.api.GlobalTransaction;
import io.mmtx.tm.api.TransactionalExecutor.ExecutionException;
import io.mmtx.tm.api.transaction.TransactionInfo;

/**
 *
 * @author lorne.cl
 */
public class MockSagaTransactionTemplate implements SagaTransactionalTemplate {

    static {
        UUIDGenerator.init(0);
    }

    @Override
    public void commitTransaction(GlobalTransaction tx) throws ExecutionException {

    }

    @Override
    public void rollbackTransaction(GlobalTransaction tx, Throwable ex) throws TransactionException, ExecutionException {

    }

    @Override
    public GlobalTransaction beginTransaction(TransactionInfo txInfo) throws ExecutionException {
        GlobalTransaction globalTransaction = new MockGlobalTransaction();
        try {
            globalTransaction.begin();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        return globalTransaction;
    }

    @Override
    public GlobalTransaction reloadTransaction(String xid) throws ExecutionException, TransactionException {
        return new MockGlobalTransaction(xid, GlobalStatus.UnKnown);
    }

    @Override
    public void reportTransaction(GlobalTransaction tx, GlobalStatus globalStatus) throws ExecutionException {

    }

    @Override
    public long branchRegister(String resourceId, String clientId, String xid, String applicationData, String lockKeys)
            throws TransactionException {
        return UUIDGenerator.generateUUID();
    }

    @Override
    public void branchReport(String xid, long branchId, BranchStatus status, String applicationData) throws TransactionException {
        
    }

    @Override
    public int getTimeout() {
        return 60000;
    }

    @Override
    public void triggerAfterCompletion() {

    }

    @Override
    public void cleanUp() {

    }
}