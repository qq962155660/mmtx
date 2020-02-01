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
package io.mmtx.spring.boot.autoconfigure;

import io.mmtx.spring.annotation.GlobalTransactionScanner;
import io.mmtx.spring.boot.autoconfigure.properties.MmtxProperties;
import io.mmtx.spring.boot.autoconfigure.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author xingfudeshi@gmail.com
 */
@ComponentScan(basePackages = "io.mmtx.spring.boot.autoconfigure.properties")
@ConditionalOnProperty(prefix = StarterConstants.MMTX_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableConfigurationProperties({MmtxProperties.class})
public class MmtxAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MmtxAutoConfiguration.class);
    @Autowired
    private MmtxProperties mmtxProperties;

    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Bean
    @DependsOn({"springUtils"})
    @ConditionalOnMissingBean(GlobalTransactionScanner.class)
    public GlobalTransactionScanner globalTransactionScanner() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Automatically configure Mmtx");
        }
        return new GlobalTransactionScanner(mmtxProperties.getApplicationId(), mmtxProperties.getTxServiceGroup());
    }


}
