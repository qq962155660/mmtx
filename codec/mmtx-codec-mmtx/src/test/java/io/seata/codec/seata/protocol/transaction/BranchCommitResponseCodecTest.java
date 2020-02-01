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
package io.mmtx.codec.mmtx.protocol.transaction;

import io.mmtx.codec.mmtx.MmtxCodec;
import io.mmtx.core.exception.TransactionExceptionCode;
import io.mmtx.core.model.BranchStatus;
import io.mmtx.core.protocol.ResultCode;
import io.mmtx.core.protocol.transaction.BranchCommitResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Branch commit response codec test.
 *
 * @author zhangsen
 */
public class BranchCommitResponseCodecTest {

    /**
     * The Mmtx codec.
     */
    MmtxCodec mmtxCodec = new MmtxCodec();

    /**
     * Test codec.
     */
    @Test
    public void test_codec(){
        BranchCommitResponse branchCommitResponse = new BranchCommitResponse();
        branchCommitResponse.setTransactionExceptionCode(TransactionExceptionCode.BranchTransactionNotExist);
        branchCommitResponse.setBranchId(123);
        branchCommitResponse.setBranchStatus(BranchStatus.PhaseOne_Done);
        branchCommitResponse.setMsg("abc");
        branchCommitResponse.setXid("a3");
        branchCommitResponse.setResultCode(ResultCode.Failed);

        byte[] bytes = mmtxCodec.encode(branchCommitResponse);

        BranchCommitResponse branchCommitResponse2 = mmtxCodec.decode(bytes);

        assertThat(branchCommitResponse2.getBranchStatus()).isEqualTo(branchCommitResponse.getBranchStatus());
        assertThat(branchCommitResponse2.getBranchId()).isEqualTo(branchCommitResponse.getBranchId());
        assertThat(branchCommitResponse2.getMsg()).isEqualTo(branchCommitResponse.getMsg());
        assertThat(branchCommitResponse2.getXid()).isEqualTo(branchCommitResponse.getXid());
        assertThat(branchCommitResponse2.getResultCode()).isEqualTo(branchCommitResponse.getResultCode());
    }

}
