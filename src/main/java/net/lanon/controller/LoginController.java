package net.lanon.controller;

import com.alibaba.fastjson.JSONObject;
import net.lanon.entiy.Login;
import net.lanon.utils.Md5;

/**
 * @author 殷若淮
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO
 * @createTime 2021年11月05日 06:26:00
 */
public class LoginController {
    public JSONObject login(Login login){
        JSONObject jsonObject = new JSONObject();
        String username = login.getUsername();
        String password = login.getPassword();
        if (Md5.EncoderByMd5(password).equals("ICy5YqxZB1uWSwcVLSNLcA==")&&"85605964".equals(username)){
            System.out.println(Md5.EncoderByMd5(password));
            jsonObject.put("code",0);
            jsonObject.put("msg","登录成功");
            return jsonObject;
        }else {
            jsonObject.put("code",-1);
            jsonObject.put("msg","登录失败");
            return jsonObject;
        }
    }
}
