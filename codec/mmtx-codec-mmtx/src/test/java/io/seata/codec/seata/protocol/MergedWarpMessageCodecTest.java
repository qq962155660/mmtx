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
package io.mmtx.codec.mmtx.protocol;

import io.mmtx.codec.mmtx.MmtxCodec;
import io.mmtx.core.protocol.AbstractMessage;
import io.mmtx.core.protocol.MergedWarpMessage;
import io.mmtx.core.protocol.transaction.GlobalBeginRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;


/**
 * The type Merged warp message codec test.
 *
 * @author zhangsen
 */
public class MergedWarpMessageCodecTest {

    /**
     * The Mmtx codec.
     */
    MmtxCodec mmtxCodec = new MmtxCodec();

    /**
     * Test codec.
     */
    @Test
    public void test_codec(){
        MergedWarpMessage mergedWarpMessage = new MergedWarpMessage();
        final ArrayList<AbstractMessage> msgs = new ArrayList<>();
        final GlobalBeginRequest globalBeginRequest1 = buildGlobalBeginRequest("x1");
        final GlobalBeginRequest globalBeginRequest2 = buildGlobalBeginRequest("x2");
        msgs.add(globalBeginRequest1);
        msgs.add(globalBeginRequest2);
        mergedWarpMessage.msgs = msgs;

        byte[] body = mmtxCodec.encode(mergedWarpMessage);

        MergedWarpMessage mergedWarpMessage2 = mmtxCodec.decode(body);
        assertThat(mergedWarpMessage2.msgs.size()).isEqualTo(mergedWarpMessage.msgs.size());

        GlobalBeginRequest globalBeginRequest21 = (GlobalBeginRequest) mergedWarpMessage2.msgs.get(0);
        assertThat(globalBeginRequest21.getTimeout()).isEqualTo(globalBeginRequest1.getTimeout());
        assertThat(globalBeginRequest21.getTransactionName()).isEqualTo(globalBeginRequest1.getTransactionName());


        GlobalBeginRequest globalBeginRequest22 = (GlobalBeginRequest) mergedWarpMessage2.msgs.get(1);
        assertThat(globalBeginRequest22.getTimeout()).isEqualTo(globalBeginRequest2.getTimeout());
        assertThat(globalBeginRequest22.getTransactionName()).isEqualTo(globalBeginRequest2.getTransactionName());

    }

    private GlobalBeginRequest buildGlobalBeginRequest(String name) {
        final GlobalBeginRequest globalBeginRequest = new GlobalBeginRequest();
        globalBeginRequest.setTransactionName(name);
        globalBeginRequest.setTimeout(3000);
        return globalBeginRequest;
    }
}
