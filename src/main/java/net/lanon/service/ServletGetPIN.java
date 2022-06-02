package net.lanon.service; /**
 * @ClassName ${NAME}.java
 * @author 殷若淮
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021年11月04日 23:06:00
 */
import com.alibaba.fastjson.JSONObject;
import net.lanon.controller.SmsController;
import net.lanon.entiy.Sms;
import net.lanon.utils.JDBCutil;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(name = "GetPIN", value = "/GetPIN")
public class ServletGetPIN extends HttpServlet {
    private static JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //定义一个公用的JdbcTemplate对象，用于查询
        JSONObject result = new JSONObject();
        Sms sms = new Sms();
        sms.setPhoneNum(request.getParameter("phonenum"));
        sms.setPinCode(String.format("%4d",new Random().nextInt(9999)));
        JSONObject jsonObject = SmsController.send(sms);
        if(jsonObject.getInteger("result")==0){
            result.put("code","0");
            result.put("msg","发送成功");
            //返回发送结果
            response.getWriter().append(result.toString());
            //将验证码存储进数据库
            //定义Sql
            String del="delete from codes where phone=?";
            template.update(del,sms.getPhoneNum());
            String sql="insert into codes(phone,code) values (?,?)";
            int update = template.update(sql,sms.getPhoneNum(),sms.getPinCode());
            System.out.println("更新结果为"+update);
            //五分钟后使验证码失效
                    Timer timer = new Timer();// 实例化Timer类
                    timer.schedule(new TimerTask() {
                        public void run() {
                            template.update(del,sms.getPhoneNum());
                            System.out.println("重置");
                            this.cancel();
                        }
                    }, 1000*60*5);
                    System.out.println("验证码将在5分钟后失效");
                }
            }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
