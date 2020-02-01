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
package io.mmtx.rm;

import io.mmtx.common.exception.FrameworkException;
import io.mmtx.common.loader.EnhancedServiceLoader;
import io.mmtx.common.util.CollectionUtils;
import io.mmtx.core.model.BranchType;
import io.mmtx.core.model.ResourceManager;
import io.mmtx.core.protocol.transaction.BranchCommitRequest;
import io.mmtx.core.protocol.transaction.BranchCommitResponse;
import io.mmtx.core.protocol.transaction.BranchRollbackRequest;
import io.mmtx.core.protocol.transaction.BranchRollbackResponse;
import io.mmtx.core.protocol.transaction.UndoLogDeleteRequest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the default RM event handler implement, deal with the phase two events
 *
 * @author zhangsen
 */
public class DefaultRMHandler extends AbstractRMHandler {

    protected static Map<BranchType, AbstractRMHandler> allRMHandlersMap
        = new ConcurrentHashMap<BranchType, AbstractRMHandler>();

    protected DefaultRMHandler() {
        initRMHandlers();
    }

    protected void initRMHandlers() {
        List<AbstractRMHandler> allRMHandlers = EnhancedServiceLoader.loadAll(AbstractRMHandler.class);
        if (CollectionUtils.isNotEmpty(allRMHandlers)) {
            for (AbstractRMHandler rmHandler : allRMHandlers) {
                allRMHandlersMap.put(rmHandler.getBranchType(), rmHandler);
            }
        }
    }

    @Override
    public BranchCommitResponse handle(BranchCommitRequest request) {
        return getRMHandler(request.getBranchType()).handle(request);
    }

    @Override
    public BranchRollbackResponse handle(BranchRollbackRequest request) {
        return getRMHandler(request.getBranchType()).handle(request);
    }

    @Override
    public void handle(UndoLogDeleteRequest request) {
        getRMHandler(request.getBranchType()).handle(request);
    }

    protected AbstractRMHandler getRMHandler(BranchType branchType) {
        return allRMHandlersMap.get(branchType);
    }

    @Override
    protected ResourceManager getResourceManager() {
        throw new FrameworkException("DefaultRMHandler isn't a real AbstractRMHandler");
    }

    private static class SingletonHolder {
        private static AbstractRMHandler INSTANCE = new DefaultRMHandler();
    }

    /**
     * Get resource manager.
     *
     * @return the resource manager
     */
    public static AbstractRMHandler get() {
        return DefaultRMHandler.SingletonHolder.INSTANCE;
    }

    @Override
    public BranchType getBranchType() {
        throw new FrameworkException("DefaultRMHandler isn't a real AbstractRMHandler");
    }
}
