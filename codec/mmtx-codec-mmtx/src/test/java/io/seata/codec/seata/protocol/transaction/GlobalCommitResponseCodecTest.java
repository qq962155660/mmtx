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
import io.mmtx.core.model.GlobalStatus;
import io.mmtx.core.protocol.ResultCode;
import io.mmtx.core.protocol.transaction.GlobalCommitResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * The type Global commit response codec test.
 *
 * @author zhangsen
 */
public class GlobalCommitResponseCodecTest {

    /**
     * The Mmtx codec.
     */
    MmtxCodec mmtxCodec = new MmtxCodec();

    /**
     * Test codec.
     */
    @Test
    public void test_codec(){
        GlobalCommitResponse globalCommitResponse = new GlobalCommitResponse();
        globalCommitResponse.setGlobalStatus(GlobalStatus.AsyncCommitting);
        globalCommitResponse.setMsg("aaa");
        globalCommitResponse.setResultCode(ResultCode.Failed);
        globalCommitResponse.setTransactionExceptionCode(TransactionExceptionCode.GlobalTransactionStatusInvalid);

        byte[] bytes = mmtxCodec.encode(globalCommitResponse);

        GlobalCommitResponse globalCommitResponse2 = mmtxCodec.decode(bytes);

        assertThat(globalCommitResponse2.getGlobalStatus()).isEqualTo(globalCommitResponse.getGlobalStatus());
        assertThat(globalCommitResponse2.getMsg()).isEqualTo(globalCommitResponse.getMsg());
        assertThat(globalCommitResponse2.getResultCode()).isEqualTo(globalCommitResponse.getResultCode());
        assertThat(globalCommitResponse2.getTransactionExceptionCode()).isEqualTo(globalCommitResponse.getTransactionExceptionCode());
    }


}
