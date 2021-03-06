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
package io.mmtx.spring.boot.autoconfigure.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.mmtx.spring.boot.autoconfigure.StarterConstants.MMTX_PREFIX;

/**
 * @author xingfudeshi@gmail.com
 */
@Component
@ConfigurationProperties(prefix = MMTX_PREFIX)
@EnableConfigurationProperties(SpringCloudAlibabaConfiguration.class)
public class MmtxProperties {
    /**
     * whether enable auto configuration
     */
    private boolean enabled = true;
    /**
     * application id
     */
    private String applicationId;
    /**
     * transaction service group
     */
    private String txServiceGroup;

    @Autowired
    private SpringCloudAlibabaConfiguration springCloudAlibabaConfiguration;

    public boolean isEnabled() {
        return enabled;
    }

    public MmtxProperties setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getApplicationId() {
        if (null == applicationId) {
            applicationId = springCloudAlibabaConfiguration.getApplicationId();
        }
        return applicationId;
    }

    public MmtxProperties setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getTxServiceGroup() {
        if (null == txServiceGroup) {
            txServiceGroup = springCloudAlibabaConfiguration.getTxServiceGroup();
        }
        return txServiceGroup;
    }

    public MmtxProperties setTxServiceGroup(String txServiceGroup) {
        this.txServiceGroup = txServiceGroup;
        return this;
    }
}
