package com.xl.bitcion;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * <br />
 * 创建于2018.03.19
 *
 * @author 谢山林
 * @version 1.0
 */

/***
 * @ServerEndpoint是一个类层次的注解,它的功能主要是把当前类定义成一个服务器端,
 * 注解的值将被用于监听用户用户连接的终端访问的URL地址,客户端可以通过这个URL地址连接到
 * Websocket服务器端
 */
@ServerEndpoint("/ws/bitcoinServer")
public class BitCoinServer {

    //与某个客户端的连接对话,需要通过它给客户端发送数据
    private Session session;

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        ServerManager.addConnect(this);
    }

    @OnClose
    public void onClose() {
        ServerManager.removeConnect(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("发生了一个错误");
        error.printStackTrace();
    }

}
