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
package io.mmtx.saga.statelang.parser.impl;

import java.util.Map;

import io.mmtx.saga.statelang.domain.impl.BaseState;

/**
 * BaseStatePaser
 *
 * @author lorne.cl
 */
public abstract class BaseStatePaser {

    protected void parseBaseAttributes(BaseState state, Object node) {

        Map<String, Object> nodeMap = (Map<String, Object>)node;
        state.setComment((String)nodeMap.get("Comment"));
        state.setNext((String)nodeMap.get("Next"));
        state.setExtensions((Map<String, Object>)nodeMap.get("Extensions"));
    }
}