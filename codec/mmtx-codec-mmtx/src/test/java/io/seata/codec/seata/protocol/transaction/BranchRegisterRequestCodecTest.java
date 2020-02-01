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
import io.mmtx.core.protocol.transaction.BranchRegisterRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Branch register request codec test.
 *
 * @author zhangsen
 */
public class BranchRegisterRequestCodecTest {

    /**
     * The Mmtx codec.
     */
    MmtxCodec mmtxCodec = new MmtxCodec();

    /**
     * Test codec.
     */
    @Test
    public void test_codec(){
        BranchRegisterRequest branchRegisterRequest = new BranchRegisterRequest();
        branchRegisterRequest.setBranchType(BranchType.AT);
        branchRegisterRequest.setApplicationData("abc");
        branchRegisterRequest.setLockKey("a:1,b:2");
        branchRegisterRequest.setResourceId("124");
        branchRegisterRequest.setXid("abc134");

        byte[] bytes = mmtxCodec.encode(branchRegisterRequest);

        BranchRegisterRequest branchRegisterRequest2 = mmtxCodec.decode(bytes);

        assertThat(branchRegisterRequest2.getBranchType()).isEqualTo(branchRegisterRequest.getBranchType());
        assertThat(branchRegisterRequest2.getApplicationData()).isEqualTo(branchRegisterRequest.getApplicationData());
        assertThat(branchRegisterRequest2.getLockKey()).isEqualTo(branchRegisterRequest.getLockKey());
        assertThat(branchRegisterRequest2.getResourceId()).isEqualTo(branchRegisterRequest.getResourceId());
        assertThat(branchRegisterRequest2.getXid()).isEqualTo(branchRegisterRequest.getXid());

    }

}
