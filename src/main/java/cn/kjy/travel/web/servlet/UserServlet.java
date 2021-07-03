package cn.kjy.travel.web.servlet;

import cn.kjy.travel.domain.ResultInfo;
import cn.kjy.travel.domain.User;
import cn.kjy.travel.service.UserService;
import cn.kjy.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author kjy
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkCodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();
        if(checkCodeServer == null || checkCodeServer.length() == 0 || (!checkCodeServer.equalsIgnoreCase(checkCode))){
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
            new ObjectMapper().writeValue(response.getWriter(), info);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();
        try{
            BeanUtils.populate(loginUser, map);
        }catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        User user = service.login(loginUser);
        if(user == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误!");
        }else if(!"Y".equals(user.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("用户未激活!请登录邮箱激活");
        }else{
            info.setFlag(true);
            session.setAttribute("user", user);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), info);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            user = new User();
        }
        new ObjectMapper().writeValue(response.getWriter(), user);
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        boolean flag = service.active(code);
        String msg;
        if(flag){
            msg = "激活成功!请<a href='login.html'>登录</a>";
        }else{
            msg = "激活失败!请联系管理员!";
        }
        response.getWriter().write(msg);
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkServer = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkServer == null || (!checkServer.equalsIgnoreCase(check))){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(info));
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo(flag);
        if(!flag){
            info.setErrorMsg("注册失败!");
        }
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(info));
    }
}
