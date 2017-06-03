package com.ibingbo.rpc.common;

/**
 * Created by bing on 17/6/3.
 */
public interface Constant {

    int ZK_SESSION_TIMEOUT = 5000;

    //在创建数据节点前，先用zkCli.sh客户端连接上服务端，查看目前存在的数据节点，
    //把下面的/zookeeper/quota改为你自己的，/zookeeper/quota是我自己Zookeeper的节点
    String ZK_REGISTRY_PATH = "/zookeeper/quota";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}
