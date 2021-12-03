package com.example.utils;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author wyl
 * @Date 13:08 2021/10/2
 **/
@ServerEndpoint(value = "/websocket/{username}")
@Component
public class MyWebSocket {

    private String username;

    /**
     * 用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        this.username=username;
        /*加入set中*/
        webSocketSet.add(this);
        System.out.println("有人加入聊天室！当前在线人数为" + webSocketSet.size());
        this.session.getAsyncRemote().sendText("当前在线人数为："+webSocketSet.size());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        /*从set中删除*/
        webSocketSet.remove(this);
        System.out.println("有人退出！当前在线人数为" + webSocketSet.size());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        System.out.println("来自客户端的消息-->:" + username + message);
        //群发消息
        broadcast(username+":"+"  "+message);
    }
    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 群发自定义消息
     * */
    public  void broadcast(String message){
        for (com.example.utils.MyWebSocket item : webSocketSet) {
            /*同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            this.session.getBasicRemote().sendText(message);*/
            /*异步发送消息.*/
            item.session.getAsyncRemote().sendText(message);
        }
    }
}
