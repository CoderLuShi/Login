package net.lanon.service; /**
 * @ClassName ${NAME}.java
 * @author 殷若淮
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021年11月05日 22:21:00
 */

import com.alibaba.fastjson.JSONObject;
import net.lanon.controller.RegisterController;
import net.lanon.entiy.Register;
import net.lanon.utils.JDBCutil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletRegister", value = "/Register")
public class ServletRegister extends HttpServlet {
    private static JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Register register = new Register();
        register.setUsername(request.getParameter("username"));
        register.setPhoneNum(request.getParameter("phoneNum"));
        register.setPassword(request.getParameter("password"));
        register.setPinCode(request.getParameter("pinCode"));
        //定义sql查询pinCode
        String sql="select code from codes where phone=?";
        String pinCode = null;
        try {
            pinCode = template.queryForObject(sql, String.class, register.getPhoneNum());
        } catch (DataAccessException e) {
            System.out.println("验证码已失效");
        }
        if (pinCode==null||(pinCode.equals(register.getPinCode())!=true)){
            response.getWriter().println("验证码错误");
        }else if (register.getPinCode().equals(pinCode)){
            JSONObject register1 = RegisterController.register(register);
            response.getWriter().append(register1.toString());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
