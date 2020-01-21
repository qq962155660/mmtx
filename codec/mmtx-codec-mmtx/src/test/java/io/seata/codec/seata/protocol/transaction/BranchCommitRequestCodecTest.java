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
import io.mmtx.core.model.BranchType;
import io.mmtx.core.protocol.transaction.BranchCommitRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Branch commit request codec test.
 *
 * @author zhangsen
 */
public class BranchCommitRequestCodecTest {

    /**
     * The Mmtx codec.
     */
    MmtxCodec mmtxCodec = new MmtxCodec();

    /**
     * Test codec.
     */
    @Test
    public void test_codec(){
        BranchCommitRequest branchCommitRequest = new BranchCommitRequest();
        branchCommitRequest.setApplicationData("abc");
        branchCommitRequest.setBranchId(123);
        branchCommitRequest.setBranchType(BranchType.AT);
        branchCommitRequest.setResourceId("t");
        branchCommitRequest.setXid("a3");

        byte[] bytes = mmtxCodec.encode(branchCommitRequest);

        BranchCommitRequest branchCommitReques2 = mmtxCodec.decode(bytes);

        assertThat(branchCommitReques2.getApplicationData()).isEqualTo(branchCommitRequest.getApplicationData());
        assertThat(branchCommitReques2.getBranchType()).isEqualTo(branchCommitRequest.getBranchType());
        assertThat(branchCommitReques2.getBranchId()).isEqualTo(branchCommitRequest.getBranchId());
        assertThat(branchCommitReques2.getResourceId()).isEqualTo(branchCommitRequest.getResourceId());
        assertThat(branchCommitReques2.getXid()).isEqualTo(branchCommitRequest.getXid());
    }

}
