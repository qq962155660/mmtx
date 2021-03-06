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
package io.mmtx.saga.statelang.domain;

import java.util.List;

/**
 * ServiceTask State, be used to invoke a service
 *
 * @author lorne.cl
 */
public interface ServiceTaskState extends TaskState {

    /**
     * Service type: such as SpringBean, SOFA RPC, default is StringBean
     *
     * @return
     */
    String getServiceType();

    /**
     * service name
     *
     * @return
     */
    String getServiceName();

    /**
     * service method
     *
     * @return
     */
    String getServiceMethod();

    /**
     * service method
     *
     * @return
     */
    List<String> getParameterTypes();

    /**
     * Is it necessary to persist the service execution log? default is true
     *
     * @return
     */
    boolean isPersist();
}