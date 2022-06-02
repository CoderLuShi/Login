package net.lanon.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import net.lanon.entiy.Sms;
import net.lanon.utils.HttpRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

/**
 * @author 殷若淮
 * @version 1.0.0
 * @ClassName SmsController.java
 * @Description TODO
 * @createTime 2021年11月05日 06:26:00
 */
public class SmsController {
    public static JSONObject send(Sms sms) {
        int appID = 1400591244;
        String appKey = "d2dbea657fbbbdd02b2c95a753e2b58e";
        String smsSign = "蓝上小栈";
        int templateID = 1184462;
        String phoneNum = sms.getPhoneNum();
        sms.setMinTime(5);
        //发送短信验证码
        try {
            String[] param = {sms.getPinCode()};
            SmsSingleSender sender = new SmsSingleSender(appID, appKey);
            SmsSingleSenderResult result = sender.sendWithParam("86", sms.getPhoneNum(), templateID, param, smsSign, "", "");
            JSONObject jsonObject = JSON.parseObject(result.toString());
            System.out.println(result);
            return jsonObject;
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
