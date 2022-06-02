package net.lanon.controller;

import com.alibaba.fastjson.JSONObject;
import net.lanon.entiy.Register;
import net.lanon.utils.JDBCutil;
import net.lanon.utils.Md5;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 殷若淮
 * @version 1.0.0
 * @ClassName RegisterController.java
 * @Description TODO
 * @createTime 2021年11月05日 06:27:00
 */
public class RegisterController {
    private static JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());
    public static JSONObject register(Register register){
        JSONObject jsonObject=new JSONObject();
        String username = register.getUsername();
        String phoneNum = register.getPhoneNum();
        String password = register.getPassword();
        password= Md5.EncoderByMd5(password);
        String sql="insert into user (username,phone,password) values(?,?,?)";
        int update = template.update(sql, username, phoneNum, password);
        if (update==1){
            jsonObject.put("code","ok");
            jsonObject.put("msg","注册成功");
            return jsonObject;
        }
        return null;
    }
}
