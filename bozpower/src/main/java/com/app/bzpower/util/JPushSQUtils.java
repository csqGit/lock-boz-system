package com.app.bzpower.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
/**
 * 用户申请
 * @author bozpower
 *
 */
public class JPushSQUtils {

    //审核app的APPKEY
    private final static String APP_KEY = "570359503dfeee84ea23cbca";
    //密钥
    private final static String MASTER_SECRET = "ccdfbaa4ee6deda632c90cca";
    private static JPushClient jpushClient;

    //给某个用户发送消息
    public static boolean pushMessageToAdmin(String userid, String title, String content) {
        jpushClient = createInstance();
        System.out.println(jpushClient);
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
