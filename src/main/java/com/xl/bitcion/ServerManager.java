package com.xl.bitcion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * <br />
 * 创建于2018.03.19
 *
 * @author 谢山林
 * @version 1.0
 */
public class ServerManager {

    private static Collection<BitCoinServer> servers =
            Collections.synchronizedCollection(new ArrayList<BitCoinServer>());

    public static void broadCast(String message) {
        for (BitCoinServer server : servers) {
            try {
                server.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getTotal() {
        return servers.size();
    }

    public static void addConnect(BitCoinServer bitCoinServer) {
        servers.add(bitCoinServer);
        System.out.println("有一个新莲接加入啦,当前连接总数是" + servers.size());
    }

    public static void removeConnect(BitCoinServer bitCoinServer) {
        servers.remove(bitCoinServer);
        System.out.println("有一个连接退出啦,当前连接数是:" + servers.size());
    }


}
