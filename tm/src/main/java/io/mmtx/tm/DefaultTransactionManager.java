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
package io.mmtx.tm;

import java.util.concurrent.TimeoutException;

import io.mmtx.core.exception.TmTransactionException;
import io.mmtx.core.exception.TransactionException;
import io.mmtx.core.exception.TransactionExceptionCode;
import io.mmtx.core.model.GlobalStatus;
import io.mmtx.core.model.TransactionManager;
import io.mmtx.core.protocol.ResultCode;
import io.mmtx.core.protocol.transaction.AbstractTransactionRequest;
import io.mmtx.core.protocol.transaction.AbstractTransactionResponse;
import io.mmtx.core.protocol.transaction.GlobalBeginRequest;
import io.mmtx.core.protocol.transaction.GlobalBeginResponse;
import io.mmtx.core.protocol.transaction.GlobalCommitRequest;
import io.mmtx.core.protocol.transaction.GlobalCommitResponse;
import io.mmtx.core.protocol.transaction.GlobalReportRequest;
import io.mmtx.core.protocol.transaction.GlobalReportResponse;
import io.mmtx.core.protocol.transaction.GlobalRollbackRequest;
import io.mmtx.core.protocol.transaction.GlobalRollbackResponse;
import io.mmtx.core.protocol.transaction.GlobalStatusRequest;
import io.mmtx.core.protocol.transaction.GlobalStatusResponse;
import io.mmtx.core.rpc.netty.TmRpcClient;

/**
 * The type Default transaction manager.
 *
 * @author sharajava
 */
public class DefaultTransactionManager implements TransactionManager {

    @Override
    public String begin(String applicationId, String transactionServiceGroup, String name, int timeout)
        throws TransactionException {
        GlobalBeginRequest request = new GlobalBeginRequest();
        request.setTransactionName(name);
        request.setTimeout(timeout);
        GlobalBeginResponse response = (GlobalBeginResponse)syncCall(request);
        if (response.getResultCode() == ResultCode.Failed) {
            throw new TmTransactionException(TransactionExceptionCode.BeginFailed, response.getMsg());
        }
        return response.getXid();
    }

    @Override
    public GlobalStatus commit(String xid) throws TransactionException {
        GlobalCommitRequest globalCommit = new GlobalCommitRequest();
        globalCommit.setXid(xid);
        GlobalCommitResponse response = (GlobalCommitResponse)syncCall(globalCommit);
        return response.getGlobalStatus();
    }

    @Override
    public GlobalStatus rollback(String xid) throws TransactionException {
        GlobalRollbackRequest globalRollback = new GlobalRollbackRequest();
        globalRollback.setXid(xid);
        GlobalRollbackResponse response = (GlobalRollbackResponse)syncCall(globalRollback);
        return response.getGlobalStatus();
    }

    @Override
    public GlobalStatus getStatus(String xid) throws TransactionException {
        GlobalStatusRequest queryGlobalStatus = new GlobalStatusRequest();
        queryGlobalStatus.setXid(xid);
        GlobalStatusResponse response = (GlobalStatusResponse)syncCall(queryGlobalStatus);
        return response.getGlobalStatus();
    }

    @Override
    public GlobalStatus globalReport(String xid, GlobalStatus globalStatus) throws TransactionException {
        GlobalReportRequest globalReport = new GlobalReportRequest();
        globalReport.setXid(xid);
        globalReport.setGlobalStatus(globalStatus);
        GlobalReportResponse response = (GlobalReportResponse) syncCall(globalReport);
        return response.getGlobalStatus();
    }

    private AbstractTransactionResponse syncCall(AbstractTransactionRequest request) throws TransactionException {
        try {
            return (AbstractTransactionResponse)TmRpcClient.getInstance().sendMsgWithResponse(request);
        } catch (TimeoutException toe) {
            throw new TmTransactionException(TransactionExceptionCode.IO, "RPC timeout", toe);
        }
    }
}
