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
package io.mmtx.discovery.registry.custom;

import io.mmtx.common.loader.EnhancedServiceLoader;
import io.mmtx.common.loader.LoadLevel;
import io.mmtx.common.util.StringUtils;
import io.mmtx.config.ConfigurationFactory;
import io.mmtx.discovery.registry.RegistryProvider;
import io.mmtx.discovery.registry.RegistryService;
import io.mmtx.discovery.registry.RegistryType;

import java.util.stream.Stream;

/**
 * @author ggndnn
 */
@LoadLevel(name = "Custom")
public class CustomRegistryProvider implements RegistryProvider {
    private static final String FILE_CONFIG_KEY_PREFIX = "registry.custom.name";

    private final String customName;

    public CustomRegistryProvider() {
        String name = ConfigurationFactory.CURRENT_FILE_INSTANCE.getConfig(FILE_CONFIG_KEY_PREFIX);
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name value of custom registry type must not be blank");
        }
        if (Stream.of(RegistryType.values())
                .anyMatch(ct -> ct.name().equalsIgnoreCase(name))) {
            throw new IllegalArgumentException(String.format("custom registry type name %s is not allowed", name));
        }
        customName = name;
    }

    @Override
    public RegistryService provide() {
        return EnhancedServiceLoader.load(RegistryProvider.class, customName).provide();
    }
}
