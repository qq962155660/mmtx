# https://hub.docker.com/orgs/mmtxio
FROM openjdk:8u232-jre-stretch

# set label
LABEL maintainer="Mmtx <mmtx.io>"

WORKDIR /$BASE_DIR

# ADD FORM distribution
ADD bin/ /mmtx-server/bin
ADD lib/ /mmtx-server/lib
ADD conf/ /mmtx-server/conf
ADD LICENSE-BIN /mmtx-server/LICENSE

# set extra environment
ENV EXTRA_JVM_ARGUMENTS="-Djava.security.egd=file:/dev/./urandom -server -Xss512k -XX:+UnlockExperimentalVMOptions -XX:+UseContainerSupport XX:SurvivorRatio=10 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -XX:MaxDirectMemorySize=1024m -XX:-OmitStackTraceInFastThrow -XX:-UseAdaptiveSizePolicy -XX:+HeapDumpOnOutOfMemoryError -XX:+DisableExplicitGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=75 -Xloggc:/var/log/mmtx_gc.log -verbose:gc -Dio.netty.leakDetectionLevel=advanced"

CMD ["sh","/mmtx-server/bin/mmtx-server.sh"]
