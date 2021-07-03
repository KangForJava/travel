package cn.kjy.travel.web.servlet;

import cn.kjy.travel.service.UserService;
import cn.kjy.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kjy
 */
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        UserService service = new UserServiceImpl();
        boolean flag = service.active(code);
        String msg;
        if(flag){
            msg = "激活成功!请<a href='login.html'>登录</a>";
        }else{
            msg = "激活失败!请联系管理员!";
        }
        response.getWriter().write(msg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
