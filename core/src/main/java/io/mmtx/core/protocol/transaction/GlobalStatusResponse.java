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
package io.mmtx.core.protocol.transaction;

import io.mmtx.core.protocol.MessageType;

/**
 * The type Global status response.
 *
 * @author slievrly
 */
public class GlobalStatusResponse extends AbstractGlobalEndResponse {

    @Override
    public short getTypeCode() {
        return MessageType.TYPE_GLOBAL_STATUS_RESULT;
    }
}
