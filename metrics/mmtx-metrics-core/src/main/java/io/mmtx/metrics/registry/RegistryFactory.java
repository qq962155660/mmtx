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
package io.mmtx.metrics.registry;

import java.util.Objects;

import io.mmtx.common.exception.NotSupportYetException;
import io.mmtx.common.loader.EnhancedServiceLoader;
import io.mmtx.common.util.StringUtils;
import io.mmtx.config.ConfigurationFactory;
import io.mmtx.core.constants.ConfigurationKeys;

/**
 * Registry Factory for load configured metrics registry
 *
 * @author zhengyangyong
 */
public class RegistryFactory {
    public static Registry getInstance() {
        RegistryType registryType;
        String registryTypeName = ConfigurationFactory.getInstance().getConfig(
            ConfigurationKeys.METRICS_PREFIX + ConfigurationKeys.METRICS_REGISTRY_TYPE, null);
        if (!StringUtils.isNullOrEmpty(registryTypeName)) {
            try {
                registryType = RegistryType.getType(registryTypeName);
            } catch (Exception exx) {
                throw new NotSupportYetException("not support metrics registry type: " + registryTypeName);
            }
            return EnhancedServiceLoader.load(Registry.class, Objects.requireNonNull(registryType).name());
        }
        return null;
    }
}
