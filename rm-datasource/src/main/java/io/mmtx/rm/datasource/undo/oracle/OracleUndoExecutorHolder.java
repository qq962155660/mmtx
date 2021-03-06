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
package io.mmtx.rm.datasource.undo.oracle;

import io.mmtx.rm.datasource.undo.AbstractUndoExecutor;
import io.mmtx.rm.datasource.undo.SQLUndoLog;
import io.mmtx.rm.datasource.undo.UndoExecutorHolder;

/**
 * The Type OracleUndoExecutorHolder
 *
 * @author: Zhibei Hao
 */
public class OracleUndoExecutorHolder implements UndoExecutorHolder {
    private static final String ORACLE = "oracle";

    @Override
    public AbstractUndoExecutor getInsertExecutor(SQLUndoLog sqlUndoLog) {
        return new OracleUndoInsertExecutor(sqlUndoLog);
    }

    @Override
    public AbstractUndoExecutor getUpdateExecutor(SQLUndoLog sqlUndoLog) {
        return new OracleUndoUpdateExecutor(sqlUndoLog);
    }

    @Override
    public AbstractUndoExecutor getDeleteExecutor(SQLUndoLog sqlUndoLog) {
        return new OracleUndoDeleteExecutor(sqlUndoLog);
    }

    @Override
    public String getDbType() {
        return ORACLE;
    }
}
