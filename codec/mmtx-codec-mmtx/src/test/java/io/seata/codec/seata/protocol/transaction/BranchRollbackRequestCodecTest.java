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
import io.mmtx.core.protocol.transaction.BranchRollbackRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Branch rollback request codec test.
 *
 * @author zhangsen
 */
public class BranchRollbackRequestCodecTest {

    /**
     * The Mmtx codec.
     */
    MmtxCodec mmtxCodec = new MmtxCodec();

    /**
     * Test codec.
     */
    @Test
    public void test_codec(){
        BranchRollbackRequest branchRollbackRequest = new BranchRollbackRequest();
        branchRollbackRequest.setApplicationData("abcd");
        branchRollbackRequest.setBranchId(112232);
        branchRollbackRequest.setBranchType(BranchType.TCC);
        branchRollbackRequest.setResourceId("343");
        branchRollbackRequest.setXid("123");

        byte[] bytes = mmtxCodec.encode(branchRollbackRequest);

        BranchRollbackRequest branchRollbackRequest2 = mmtxCodec.decode(bytes);

        assertThat(branchRollbackRequest2.getApplicationData()).isEqualTo(branchRollbackRequest.getApplicationData());
        assertThat(branchRollbackRequest2.getBranchId()).isEqualTo(branchRollbackRequest.getBranchId());
        assertThat(branchRollbackRequest2.getBranchType()).isEqualTo(branchRollbackRequest.getBranchType());
        assertThat(branchRollbackRequest2.getResourceId()).isEqualTo(branchRollbackRequest.getResourceId());
        assertThat(branchRollbackRequest2.getXid()).isEqualTo(branchRollbackRequest.getXid());

    }

}
