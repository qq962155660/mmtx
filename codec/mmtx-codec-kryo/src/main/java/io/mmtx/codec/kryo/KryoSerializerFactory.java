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
package io.mmtx.codec.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import de.javakaffee.kryoserializers.ArraysAsListSerializer;
import de.javakaffee.kryoserializers.BitSetSerializer;
import de.javakaffee.kryoserializers.GregorianCalendarSerializer;
import de.javakaffee.kryoserializers.JdkProxySerializer;
import de.javakaffee.kryoserializers.RegexSerializer;
import de.javakaffee.kryoserializers.URISerializer;
import de.javakaffee.kryoserializers.UUIDSerializer;
import io.mmtx.core.protocol.MergeResultMessage;
import io.mmtx.core.protocol.MergedWarpMessage;
import io.mmtx.core.protocol.RegisterRMRequest;
import io.mmtx.core.protocol.RegisterRMResponse;
import io.mmtx.core.protocol.RegisterTMRequest;
import io.mmtx.core.protocol.RegisterTMResponse;
import io.mmtx.core.protocol.transaction.BranchCommitRequest;
import io.mmtx.core.protocol.transaction.BranchCommitResponse;
import io.mmtx.core.protocol.transaction.BranchRegisterRequest;
import io.mmtx.core.protocol.transaction.BranchRegisterResponse;
import io.mmtx.core.protocol.transaction.BranchReportRequest;
import io.mmtx.core.protocol.transaction.BranchReportResponse;
import io.mmtx.core.protocol.transaction.BranchRollbackRequest;
import io.mmtx.core.protocol.transaction.BranchRollbackResponse;
import io.mmtx.core.protocol.transaction.GlobalBeginRequest;
import io.mmtx.core.protocol.transaction.GlobalBeginResponse;
import io.mmtx.core.protocol.transaction.GlobalCommitRequest;
import io.mmtx.core.protocol.transaction.GlobalCommitResponse;
import io.mmtx.core.protocol.transaction.GlobalLockQueryRequest;
import io.mmtx.core.protocol.transaction.GlobalLockQueryResponse;
import io.mmtx.core.protocol.transaction.GlobalReportRequest;
import io.mmtx.core.protocol.transaction.GlobalReportResponse;
import io.mmtx.core.protocol.transaction.GlobalRollbackRequest;
import io.mmtx.core.protocol.transaction.GlobalRollbackResponse;
import io.mmtx.core.protocol.transaction.GlobalStatusRequest;
import io.mmtx.core.protocol.transaction.GlobalStatusResponse;
import io.mmtx.core.protocol.transaction.UndoLogDeleteRequest;

import java.lang.reflect.InvocationHandler;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author jsbxyyx
 */
public class KryoSerializerFactory implements KryoFactory {

    private static final KryoSerializerFactory FACTORY = new KryoSerializerFactory();

    private KryoPool pool = new KryoPool.Builder(this).softReferences().build();

    private KryoSerializerFactory() {}

    public static KryoSerializerFactory getInstance() {
        return FACTORY;
    }

    public KryoSerializer get() {
        return new KryoSerializer(pool.borrow());
    }

    public void returnKryo(KryoSerializer kryoSerializer) {
        if (kryoSerializer == null) {
            throw new IllegalArgumentException("kryoSerializer is null");
        }
        pool.release(kryoSerializer.getKryo());
    }

    @Override
    public Kryo create() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        // register serializer
        kryo.register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
        kryo.register(GregorianCalendar.class, new GregorianCalendarSerializer());
        kryo.register(InvocationHandler.class, new JdkProxySerializer());
        kryo.register(BigDecimal.class, new DefaultSerializers.BigDecimalSerializer());
        kryo.register(BigInteger.class, new DefaultSerializers.BigIntegerSerializer());
        kryo.register(Pattern.class, new RegexSerializer());
        kryo.register(BitSet.class, new BitSetSerializer());
        kryo.register(URI.class, new URISerializer());
        kryo.register(UUID.class, new UUIDSerializer());

        // register commonly class
        kryo.register(HashMap.class);
        kryo.register(ArrayList.class);
        kryo.register(LinkedList.class);
        kryo.register(HashSet.class);
        kryo.register(TreeSet.class);
        kryo.register(Hashtable.class);
        kryo.register(Date.class);
        kryo.register(Calendar.class);
        kryo.register(ConcurrentHashMap.class);
        kryo.register(SimpleDateFormat.class);
        kryo.register(GregorianCalendar.class);
        kryo.register(Vector.class);
        kryo.register(BitSet.class);
        kryo.register(StringBuffer.class);
        kryo.register(StringBuilder.class);
        kryo.register(Object.class);
        kryo.register(Object[].class);
        kryo.register(String[].class);
        kryo.register(byte[].class);
        kryo.register(char[].class);
        kryo.register(int[].class);
        kryo.register(float[].class);
        kryo.register(double[].class);

        // register mmtx protocol relation class
        kryo.register(BranchCommitRequest.class);
        kryo.register(BranchCommitResponse.class);
        kryo.register(BranchRegisterRequest.class);
        kryo.register(BranchRegisterResponse.class);
        kryo.register(BranchReportRequest.class);
        kryo.register(BranchReportResponse.class);
        kryo.register(BranchRollbackRequest.class);
        kryo.register(BranchRollbackResponse.class);
        kryo.register(GlobalBeginRequest.class);
        kryo.register(GlobalBeginResponse.class);
        kryo.register(GlobalCommitRequest.class);
        kryo.register(GlobalCommitResponse.class);
        kryo.register(GlobalLockQueryRequest.class);
        kryo.register(GlobalLockQueryResponse.class);
        kryo.register(GlobalRollbackRequest.class);
        kryo.register(GlobalRollbackResponse.class);
        kryo.register(GlobalStatusRequest.class);
        kryo.register(GlobalStatusResponse.class);
        kryo.register(UndoLogDeleteRequest.class);
        kryo.register(GlobalReportRequest.class);
        kryo.register(GlobalReportResponse.class);

        kryo.register(MergedWarpMessage.class);
        kryo.register(MergeResultMessage.class);
        kryo.register(RegisterRMRequest.class);
        kryo.register(RegisterRMResponse.class);
        kryo.register(RegisterTMRequest.class);
        kryo.register(RegisterTMResponse.class);

        return kryo;
    }

}
