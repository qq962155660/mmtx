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
import io.mmtx.core.protocol.ResultCode;
import io.mmtx.core.protocol.transaction.BranchRegisterResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Branch register response codec test.
 *
 * @author zhangsen
 */
public class BranchRegisterResponseCodecTest {

    /**
     * The Mmtx codec.
     */
    MmtxCodec mmtxCodec = new MmtxCodec();

    /**
     * Test codec.
     */
    @Test
    public void test_codec(){
        BranchRegisterResponse branchRegisterResponse = new BranchRegisterResponse();
        branchRegisterResponse.setBranchId(1346);
        branchRegisterResponse.setMsg("addd");
        branchRegisterResponse.setResultCode(ResultCode.Failed);
        branchRegisterResponse.setTransactionExceptionCode(TransactionExceptionCode.BranchTransactionNotExist);

        byte[] bytes = mmtxCodec.encode(branchRegisterResponse);

        BranchRegisterResponse branchRegisterResponse2 = mmtxCodec.decode(bytes);

        assertThat(branchRegisterResponse2.getBranchId()).isEqualTo(branchRegisterResponse.getBranchId());
        assertThat(branchRegisterResponse2.getMsg()).isEqualTo(branchRegisterResponse.getMsg());
        assertThat(branchRegisterResponse2.getResultCode()).isEqualTo(branchRegisterResponse.getResultCode());
        assertThat(branchRegisterResponse2.getTransactionExceptionCode()).isEqualTo(branchRegisterResponse.getTransactionExceptionCode());
    }

}
