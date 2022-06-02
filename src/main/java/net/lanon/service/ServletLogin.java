package net.lanon.service; /**
 * @ClassName ${NAME}.java
 * @author 殷若淮
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021年11月05日 13:20:00
 */

import com.alibaba.fastjson.JSONObject;
import net.lanon.controller.LoginController;
import net.lanon.entiy.Login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletLogin", value = "/Login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Login login = new Login();
        login.setUsername(request.getParameter("username"));
        login.setPassword(request.getParameter("password"));
        LoginController loginController = new LoginController();
        JSONObject login1 = loginController.login(login);
        response.getWriter().println(login1.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
