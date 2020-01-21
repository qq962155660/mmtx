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
package io.mmtx.discovery.registry.etcd3;

import io.mmtx.common.loader.LoadLevel;
import io.mmtx.discovery.registry.RegistryProvider;
import io.mmtx.discovery.registry.RegistryService;

/**
 * @author xingfudeshi@gmail.com
 */
@LoadLevel(name = "Etcd3", order = 1)
public class EtcdRegistryProvider implements RegistryProvider {
    @Override
    public RegistryService provide() {
        return EtcdRegistryServiceImpl.getInstance();
    }
}
