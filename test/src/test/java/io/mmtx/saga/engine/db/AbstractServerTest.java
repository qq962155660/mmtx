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
package io.mmtx.saga.engine.db;

import io.mmtx.common.XID;
import io.mmtx.common.util.NetUtil;
import io.mmtx.core.constants.ConfigurationKeys;
import io.mmtx.core.rpc.netty.RpcServer;
import io.mmtx.core.rpc.netty.ShutdownHook;
import io.mmtx.server.ParameterParser;
import io.mmtx.server.UUIDGenerator;
import io.mmtx.server.coordinator.DefaultCoordinator;
import io.mmtx.server.metrics.MetricsManager;
import io.mmtx.server.session.SessionHolder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Abstract Server Test
 *
 * @author lorne.cl
 */
public abstract class AbstractServerTest {


    private static RpcServer rpcServer;
    private static final ThreadPoolExecutor workingThreads = new ThreadPoolExecutor(100, 500, 500, TimeUnit.SECONDS,
            new LinkedBlockingQueue(20000), new ThreadPoolExecutor.CallerRunsPolicy());

    protected static void startMmtxServer() throws InterruptedException {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File("sessionStore/root.data");
                    if(file.exists()){
                        file.delete();
                    }

                    ParameterParser parameterParser = new ParameterParser(new String[]{});

                    //initialize the metrics
                    MetricsManager.get().init();

                    System.setProperty(ConfigurationKeys.STORE_MODE, parameterParser.getStoreMode());

                    rpcServer = new RpcServer(workingThreads);
                    //server port
                    rpcServer.setListenPort(parameterParser.getPort());
                    UUIDGenerator.init(parameterParser.getServerNode());
                    //log store mode : file、db
                    SessionHolder.init(parameterParser.getStoreMode());

                    DefaultCoordinator coordinator = new DefaultCoordinator(rpcServer);
                    coordinator.init();
                    rpcServer.setHandler(coordinator);
                    // register ShutdownHook
                    ShutdownHook.getInstance().addDisposable(coordinator);

                    //127.0.0.1 and 0.0.0.0 are not valid here.
                    if (NetUtil.isValidIp(parameterParser.getHost(), false)) {
                        XID.setIpAddress(parameterParser.getHost());
                    } else {
                        XID.setIpAddress(NetUtil.getLocalIp());
                    }
                    XID.setPort(rpcServer.getListenPort());

                    rpcServer.init();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        })).start();
        Thread.sleep(5000);
    }

    protected static final void stopMmtxServer() throws InterruptedException {
        if(rpcServer != null){
            rpcServer.destroy();
            Thread.sleep(5000);
        }
    }

}