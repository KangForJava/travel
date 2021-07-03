package cn.kjy.travel.web.servlet;

import cn.kjy.travel.domain.ResultInfo;
import cn.kjy.travel.domain.User;
import cn.kjy.travel.service.UserService;
import cn.kjy.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author kjy
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        UserService service = new UserServiceImpl();
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
