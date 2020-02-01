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
import io.mmtx.core.model.GlobalStatus;
import io.mmtx.saga.engine.sequence.SpringJvmUUIDSeqGenerator;
import io.mmtx.tm.api.GlobalTransaction;

/**
 *
 * @author lorne.cl
 */
public class MockGlobalTransaction implements GlobalTransaction {

    private String xid;
    private GlobalStatus status;

    private static SpringJvmUUIDSeqGenerator uuidSeqGenerator = new SpringJvmUUIDSeqGenerator();

    public MockGlobalTransaction() {}

    public MockGlobalTransaction(String xid) {
        this.xid = xid;
    }

    public MockGlobalTransaction(String xid, GlobalStatus status) {
        this.xid = xid;
        this.status = status;
    }

    @Override
    public void begin() throws TransactionException {
        begin(60000);
    }

    @Override
    public void begin(int timeout) throws TransactionException {
        status = GlobalStatus.Begin;
        xid = uuidSeqGenerator.generate(null).toString();
    }

    @Override
    public void begin(int timeout, String name) throws TransactionException {

    }

    @Override
    public void commit() throws TransactionException {

    }

    @Override
    public void rollback() throws TransactionException {

    }

    @Override
    public GlobalStatus getStatus() throws TransactionException {
        return status;
    }

    @Override
    public String getXid() {
        return xid;
    }

    @Override
    public void globalReport(GlobalStatus globalStatus) throws TransactionException {

    }
}