package com.ibingbo.rpc.common;

/**
 * Created by bing on 17/6/2.
 */
public interface Constant {
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTRY_PATH = "/zookeeper/quota";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}
