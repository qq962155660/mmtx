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
package io.mmtx.codec.protobuf.convertor;

import io.mmtx.codec.protobuf.generated.AbstractMessageProto;
import io.mmtx.codec.protobuf.generated.AbstractTransactionRequestProto;
import io.mmtx.codec.protobuf.generated.BranchReportRequestProto;
import io.mmtx.codec.protobuf.generated.BranchStatusProto;
import io.mmtx.codec.protobuf.generated.BranchTypeProto;
import io.mmtx.codec.protobuf.generated.MessageTypeProto;
import io.mmtx.core.model.BranchStatus;
import io.mmtx.core.model.BranchType;
import io.mmtx.core.protocol.transaction.BranchReportRequest;

/**
 * @author leizhiyuan
 */
public class BranchReportRequestConvertor implements PbConvertor<BranchReportRequest, BranchReportRequestProto> {
    @Override
    public BranchReportRequestProto convert2Proto(BranchReportRequest branchReportRequest) {
        final short typeCode = branchReportRequest.getTypeCode();

        final AbstractMessageProto abstractMessage = AbstractMessageProto.newBuilder().setMessageType(
            MessageTypeProto.forNumber(typeCode)).build();

        final AbstractTransactionRequestProto abstractTransactionRequestProto = AbstractTransactionRequestProto
            .newBuilder().setAbstractMessage(abstractMessage).build();

        final String applicationData = branchReportRequest.getApplicationData();
        final String resourceId = branchReportRequest.getResourceId();
        BranchReportRequestProto result = BranchReportRequestProto.newBuilder().setAbstractTransactionRequest(
            abstractTransactionRequestProto).setXid(branchReportRequest.getXid()).setBranchId(
            branchReportRequest.getBranchId()).setBranchType(
            BranchTypeProto.valueOf(branchReportRequest.getBranchType().name())).setApplicationData(
            applicationData == null ? "" : applicationData).setResourceId(resourceId == null ? "" : resourceId)
            .setStatus(BranchStatusProto.valueOf(branchReportRequest.getStatus().name())).build();

        return result;
    }

    @Override
    public BranchReportRequest convert2Model(BranchReportRequestProto branchReportRequestProto) {
        BranchReportRequest branchReportRequest = new BranchReportRequest();
        branchReportRequest.setApplicationData(branchReportRequestProto.getApplicationData());
        branchReportRequest.setBranchId(branchReportRequestProto.getBranchId());
        branchReportRequest.setResourceId(branchReportRequestProto.getResourceId());
        branchReportRequest.setXid(branchReportRequestProto.getXid());
        branchReportRequest.setBranchType(BranchType.valueOf(branchReportRequestProto.getBranchType().name()));
        branchReportRequest.setStatus(BranchStatus.valueOf(branchReportRequestProto.getStatus().name()));
        return branchReportRequest;
    }
}