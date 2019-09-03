package com.app.bzpower.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 管理员审核
 * @author bozpower
 *
 */
public class JPushSHUtils {

    //申请app的APPKEY
    private final static String APP_KEY = "916af83d8fb94c8268d79e00";
    //密钥
    private final static String MASTER_SECRET = "6c829c085a547bb51387bcd6";
    private static JPushClient jpushClient;

    //给某个用户发送消息
    public static boolean pushMessageToUser(String userid, String title, String content) {
        jpushClient = createInstance();
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(userid))
                .setNotification(Notification.android(content, title, null))
                .build();
        try {
           PushResult result =  jpushClient.sendPush(payload);
           System.out.println(result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 创建极光推送客户端，单例模式
     * @return
     */
    private static JPushClient createInstance() {
        return jpushClient == null ? new JPushClient(MASTER_SECRET, APP_KEY) : jpushClient;
    }
}
