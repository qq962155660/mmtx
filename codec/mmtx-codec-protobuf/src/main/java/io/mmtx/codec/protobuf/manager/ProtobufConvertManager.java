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
package io.mmtx.codec.protobuf.manager;

import io.mmtx.codec.protobuf.convertor.BranchCommitRequestConvertor;
import io.mmtx.codec.protobuf.convertor.BranchCommitResponseConvertor;
import io.mmtx.codec.protobuf.convertor.BranchRegisterRequestConvertor;
import io.mmtx.codec.protobuf.convertor.BranchRegisterResponseConvertor;
import io.mmtx.codec.protobuf.convertor.BranchReportRequestConvertor;
import io.mmtx.codec.protobuf.convertor.BranchReportResponseConvertor;
import io.mmtx.codec.protobuf.convertor.BranchRollbackRequestConvertor;
import io.mmtx.codec.protobuf.convertor.BranchRollbackResponseConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalBeginRequestConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalBeginResponseConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalCommitRequestConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalCommitResponseConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalLockQueryRequestConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalLockQueryResponseConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalReportRequestConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalReportResponseConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalRollbackRequestConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalRollbackResponseConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalStatusRequestConvertor;
import io.mmtx.codec.protobuf.convertor.GlobalStatusResponseConvertor;
import io.mmtx.codec.protobuf.convertor.HeartbeatMessageConvertor;
import io.mmtx.codec.protobuf.convertor.MergeResultMessageConvertor;
import io.mmtx.codec.protobuf.convertor.MergedWarpMessageConvertor;
import io.mmtx.codec.protobuf.convertor.PbConvertor;
import io.mmtx.codec.protobuf.convertor.RegisterRMRequestConvertor;
import io.mmtx.codec.protobuf.convertor.RegisterRMResponseConvertor;
import io.mmtx.codec.protobuf.convertor.RegisterTMRequestConvertor;
import io.mmtx.codec.protobuf.convertor.RegisterTMResponseConvertor;
import io.mmtx.codec.protobuf.generated.GlobalReportRequestProto;
import io.mmtx.codec.protobuf.generated.GlobalReportResponseProto;
import io.mmtx.core.protocol.HeartbeatMessage;
import io.mmtx.core.protocol.MergeResultMessage;
import io.mmtx.core.protocol.MergedWarpMessage;
import io.mmtx.core.protocol.RegisterRMRequest;
import io.mmtx.core.protocol.RegisterRMResponse;
import io.mmtx.core.protocol.RegisterTMRequest;
import io.mmtx.core.protocol.RegisterTMResponse;
import io.mmtx.codec.protobuf.convertor.UndoLogDeleteRequestConvertor;
import io.mmtx.codec.protobuf.generated.BranchCommitRequestProto;
import io.mmtx.codec.protobuf.generated.BranchCommitResponseProto;
import io.mmtx.codec.protobuf.generated.BranchRegisterRequestProto;
import io.mmtx.codec.protobuf.generated.BranchRegisterResponseProto;
import io.mmtx.codec.protobuf.generated.BranchReportRequestProto;
import io.mmtx.codec.protobuf.generated.BranchReportResponseProto;
import io.mmtx.codec.protobuf.generated.BranchRollbackRequestProto;
import io.mmtx.codec.protobuf.generated.BranchRollbackResponseProto;
import io.mmtx.codec.protobuf.generated.GlobalBeginRequestProto;
import io.mmtx.codec.protobuf.generated.GlobalBeginResponseProto;
import io.mmtx.codec.protobuf.generated.GlobalCommitRequestProto;
import io.mmtx.codec.protobuf.generated.GlobalCommitResponseProto;
import io.mmtx.codec.protobuf.generated.GlobalLockQueryRequestProto;
import io.mmtx.codec.protobuf.generated.GlobalLockQueryResponseProto;
import io.mmtx.codec.protobuf.generated.GlobalRollbackRequestProto;
import io.mmtx.codec.protobuf.generated.GlobalRollbackResponseProto;
import io.mmtx.codec.protobuf.generated.GlobalStatusRequestProto;
import io.mmtx.codec.protobuf.generated.GlobalStatusResponseProto;
import io.mmtx.codec.protobuf.generated.HeartbeatMessageProto;
import io.mmtx.codec.protobuf.generated.MergedResultMessageProto;
import io.mmtx.codec.protobuf.generated.MergedWarpMessageProto;
import io.mmtx.codec.protobuf.generated.RegisterRMRequestProto;
import io.mmtx.codec.protobuf.generated.RegisterRMResponseProto;
import io.mmtx.codec.protobuf.generated.RegisterTMRequestProto;
import io.mmtx.codec.protobuf.generated.RegisterTMResponseProto;
import io.mmtx.codec.protobuf.generated.UndoLogDeleteRequestProto;
import io.mmtx.core.protocol.transaction.BranchCommitRequest;
import io.mmtx.core.protocol.transaction.BranchCommitResponse;
import io.mmtx.core.protocol.transaction.BranchRegisterRequest;
import io.mmtx.core.protocol.transaction.BranchRegisterResponse;
import io.mmtx.core.protocol.transaction.BranchReportRequest;
import io.mmtx.core.protocol.transaction.BranchReportResponse;
import io.mmtx.core.protocol.transaction.BranchRollbackRequest;
import io.mmtx.core.protocol.transaction.BranchRollbackResponse;
import io.mmtx.core.protocol.transaction.GlobalBeginRequest;
import io.mmtx.core.protocol.transaction.GlobalBeginResponse;
import io.mmtx.core.protocol.transaction.GlobalCommitRequest;
import io.mmtx.core.protocol.transaction.GlobalCommitResponse;
import io.mmtx.core.protocol.transaction.GlobalLockQueryRequest;
import io.mmtx.core.protocol.transaction.GlobalLockQueryResponse;
import io.mmtx.core.protocol.transaction.GlobalReportRequest;
import io.mmtx.core.protocol.transaction.GlobalReportResponse;
import io.mmtx.core.protocol.transaction.GlobalRollbackRequest;
import io.mmtx.core.protocol.transaction.GlobalRollbackResponse;
import io.mmtx.core.protocol.transaction.GlobalStatusRequest;
import io.mmtx.core.protocol.transaction.GlobalStatusResponse;
import io.mmtx.core.protocol.transaction.UndoLogDeleteRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leizhiyuan
 */
public class ProtobufConvertManager {

    private Map<String, PbConvertor> convertorMap = new ConcurrentHashMap<>();

    private Map<String, PbConvertor> reverseConvertorMap = new ConcurrentHashMap<>();

    private Map<String, Class> protoClazzMap = new ConcurrentHashMap<>();

    private static class SingletonHolder {
        private static final ProtobufConvertManager INSTANCE;

        static {
            final ProtobufConvertManager protobufConvertManager = new ProtobufConvertManager();
            protobufConvertManager.convertorMap.put(GlobalBeginRequest.class.getName(),
                new GlobalBeginRequestConvertor());
            protobufConvertManager.convertorMap.put(BranchCommitRequest.class.getName(),
                new BranchCommitRequestConvertor());
            protobufConvertManager.convertorMap.put(BranchCommitResponse.class.getName(),
                new BranchCommitResponseConvertor());
            protobufConvertManager.convertorMap.put(BranchRegisterRequest.class.getName(),
                new BranchRegisterRequestConvertor());
            protobufConvertManager.convertorMap.put(BranchRegisterResponse.class.getName(),
                new BranchRegisterResponseConvertor());
            protobufConvertManager.convertorMap.put(BranchReportRequest.class.getName(),
                new BranchReportRequestConvertor());
            protobufConvertManager.convertorMap.put(BranchReportResponse.class.getName(),
                new BranchReportResponseConvertor());
            protobufConvertManager.convertorMap.put(BranchRollbackRequest.class.getName(),
                new BranchRollbackRequestConvertor());
            protobufConvertManager.convertorMap.put(BranchRollbackResponse.class.getName(),
                new BranchRollbackResponseConvertor());
            protobufConvertManager.convertorMap.put(GlobalBeginResponse.class.getName(),
                new GlobalBeginResponseConvertor());
            protobufConvertManager.convertorMap.put(GlobalCommitRequest.class.getName(),
                new GlobalCommitRequestConvertor());
            protobufConvertManager.convertorMap.put(GlobalCommitResponse.class.getName(),
                new GlobalCommitResponseConvertor());
            protobufConvertManager.convertorMap.put(GlobalLockQueryRequest.class.getName(),
                new GlobalLockQueryRequestConvertor());
            protobufConvertManager.convertorMap.put(GlobalLockQueryResponse.class.getName(),
                new GlobalLockQueryResponseConvertor());
            protobufConvertManager.convertorMap.put(GlobalRollbackRequest.class.getName(),
                new GlobalRollbackRequestConvertor());
            protobufConvertManager.convertorMap.put(GlobalRollbackResponse.class.getName(),
                new GlobalRollbackResponseConvertor());
            protobufConvertManager.convertorMap.put(GlobalStatusRequest.class.getName(),
                new GlobalStatusRequestConvertor());
            protobufConvertManager.convertorMap.put(GlobalStatusResponse.class.getName(),
                new GlobalStatusResponseConvertor());
            protobufConvertManager.convertorMap.put(GlobalReportRequest.class.getName(),
                new GlobalReportRequestConvertor());
            protobufConvertManager.convertorMap.put(GlobalReportResponse.class.getName(),
                new GlobalReportResponseConvertor());
            protobufConvertManager.convertorMap.put(UndoLogDeleteRequest.class.getName(),
                new UndoLogDeleteRequestConvertor());

            protobufConvertManager.convertorMap.put(MergedWarpMessage.class.getName(),
                new MergedWarpMessageConvertor());
            protobufConvertManager.convertorMap.put(HeartbeatMessage.class.getName(), new HeartbeatMessageConvertor());
            protobufConvertManager.convertorMap.put(MergeResultMessage.class.getName(),
                new MergeResultMessageConvertor());
            protobufConvertManager.convertorMap.put(RegisterRMRequest.class.getName(),
                new RegisterRMRequestConvertor());
            protobufConvertManager.convertorMap.put(RegisterRMResponse.class.getName(),
                new RegisterRMResponseConvertor());
            protobufConvertManager.convertorMap.put(RegisterTMRequest.class.getName(),
                new RegisterTMRequestConvertor());
            protobufConvertManager.convertorMap.put(RegisterTMResponse.class.getName(),
                new RegisterTMResponseConvertor());

            protobufConvertManager.protoClazzMap.put(GlobalBeginRequestProto.getDescriptor().getFullName(),
                GlobalBeginRequestProto.class);
            protobufConvertManager.protoClazzMap.put(BranchCommitRequestProto.getDescriptor().getFullName(),
                BranchCommitRequestProto.class);
            protobufConvertManager.protoClazzMap.put(BranchCommitResponseProto.getDescriptor().getFullName(),
                BranchCommitResponseProto.class);
            protobufConvertManager.protoClazzMap.put(BranchRegisterRequestProto.getDescriptor().getFullName(),
                BranchRegisterRequestProto.class);
            protobufConvertManager.protoClazzMap.put(BranchRegisterResponseProto.getDescriptor().getFullName(),
                BranchRegisterResponseProto.class);
            protobufConvertManager.protoClazzMap.put(BranchReportRequestProto.getDescriptor().getFullName(),
                BranchReportRequestProto.class);
            protobufConvertManager.protoClazzMap.put(BranchReportResponseProto.getDescriptor().getFullName(),
                BranchReportResponseProto.class);
            protobufConvertManager.protoClazzMap.put(BranchRollbackRequestProto.getDescriptor().getFullName(),
                BranchRollbackRequestProto.class);
            protobufConvertManager.protoClazzMap.put(BranchRollbackResponseProto.getDescriptor().getFullName(),
                BranchRollbackResponseProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalBeginResponseProto.getDescriptor().getFullName(),
                GlobalBeginResponseProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalCommitRequestProto.getDescriptor().getFullName(),
                GlobalCommitRequestProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalCommitResponseProto.getDescriptor().getFullName(),
                GlobalCommitResponseProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalLockQueryRequestProto.getDescriptor().getFullName(),
                GlobalLockQueryRequestProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalLockQueryResponseProto.getDescriptor().getFullName(),
                GlobalLockQueryResponseProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalRollbackRequestProto.getDescriptor().getFullName(),
                GlobalRollbackRequestProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalRollbackResponseProto.getDescriptor().getFullName(),
                GlobalRollbackResponseProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalStatusRequestProto.getDescriptor().getFullName(),
                GlobalStatusRequestProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalStatusResponseProto.getDescriptor().getFullName(),
                GlobalStatusResponseProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalReportRequestProto.getDescriptor().getFullName(),
                GlobalReportRequestProto.class);
            protobufConvertManager.protoClazzMap.put(GlobalReportResponseProto.getDescriptor().getFullName(),
                GlobalReportResponseProto.class);
            protobufConvertManager.protoClazzMap.put(UndoLogDeleteRequestProto.getDescriptor().getFullName(),
                UndoLogDeleteRequestProto.class);

            protobufConvertManager.protoClazzMap.put(MergedWarpMessageProto.getDescriptor().getFullName(),
                MergedWarpMessageProto.class);
            protobufConvertManager.protoClazzMap.put(HeartbeatMessageProto.getDescriptor().getFullName(),
                HeartbeatMessageProto.class);
            protobufConvertManager.protoClazzMap.put(MergedResultMessageProto.getDescriptor().getFullName(),
                MergedResultMessageProto.class);
            protobufConvertManager.protoClazzMap.put(RegisterRMRequestProto.getDescriptor().getFullName(),
                RegisterRMRequestProto.class);
            protobufConvertManager.protoClazzMap.put(RegisterRMResponseProto.getDescriptor().getFullName(),
                RegisterRMResponseProto.class);
            protobufConvertManager.protoClazzMap.put(RegisterTMRequestProto.getDescriptor().getFullName(),
                RegisterTMRequestProto.class);
            protobufConvertManager.protoClazzMap.put(RegisterTMResponseProto.getDescriptor().getFullName(),
                RegisterTMResponseProto.class);

            protobufConvertManager.reverseConvertorMap.put(GlobalBeginRequestProto.class.getName(),
                new GlobalBeginRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchCommitRequestProto.class.getName(),
                new BranchCommitRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchCommitResponseProto.class.getName(),
                new BranchCommitResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchRegisterRequestProto.class.getName(),
                new BranchRegisterRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchRegisterResponseProto.class.getName(),
                new BranchRegisterResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchReportRequestProto.class.getName(),
                new BranchReportRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchReportResponseProto.class.getName(),
                new BranchReportResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchRollbackRequestProto.class.getName(),
                new BranchRollbackRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(BranchRollbackResponseProto.class.getName(),
                new BranchRollbackResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalBeginResponseProto.class.getName(),
                new GlobalBeginResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalCommitRequestProto.class.getName(),
                new GlobalCommitRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalCommitResponseProto.class.getName(),
                new GlobalCommitResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalLockQueryRequestProto.class.getName(),
                new GlobalLockQueryRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalLockQueryResponseProto.class.getName(),
                new GlobalLockQueryResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalRollbackRequestProto.class.getName(),
                new GlobalRollbackRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalRollbackResponseProto.class.getName(),
                new GlobalRollbackResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalStatusRequestProto.class.getName(),
                new GlobalStatusRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalStatusResponseProto.class.getName(),
                new GlobalStatusResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalReportRequestProto.class.getName(),
                new GlobalReportRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(GlobalReportResponseProto.class.getName(),
                new GlobalReportResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(UndoLogDeleteRequestProto.class.getName(),
                new UndoLogDeleteRequestConvertor());

            protobufConvertManager.reverseConvertorMap.put(MergedWarpMessageProto.class.getName(),
                new MergedWarpMessageConvertor());
            protobufConvertManager.reverseConvertorMap.put(HeartbeatMessageProto.class.getName(),
                new HeartbeatMessageConvertor());
            protobufConvertManager.reverseConvertorMap.put(MergedResultMessageProto.class.getName(),
                new MergeResultMessageConvertor());
            protobufConvertManager.reverseConvertorMap.put(RegisterRMRequestProto.class.getName(),
                new RegisterRMRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(RegisterRMResponseProto.class.getName(),
                new RegisterRMResponseConvertor());
            protobufConvertManager.reverseConvertorMap.put(RegisterTMRequestProto.class.getName(),
                new RegisterTMRequestConvertor());
            protobufConvertManager.reverseConvertorMap.put(RegisterTMResponseProto.class.getName(),
                new RegisterTMResponseConvertor());

            INSTANCE = protobufConvertManager;
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static final ProtobufConvertManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public PbConvertor fetchConvertor(String clazz) {
        return convertorMap.get(clazz);
    }

    public PbConvertor fetchReversedConvertor(String clazz) {
        return reverseConvertorMap.get(clazz);
    }

    public Class fetchProtoClass(String clazz) {
        return protoClazzMap.get(clazz);
    }

}