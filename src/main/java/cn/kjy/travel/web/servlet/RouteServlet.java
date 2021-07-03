package cn.kjy.travel.web.servlet;

import cn.kjy.travel.domain.PageBean;
import cn.kjy.travel.domain.Route;
import cn.kjy.travel.domain.User;
import cn.kjy.travel.service.FavoriteService;
import cn.kjy.travel.service.RouteService;
import cn.kjy.travel.service.impl.FavoriteServiceImpl;
import cn.kjy.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kjy
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String keyWord = request.getParameter("rname");
        if(cid == null || cid.length() == 0){
            cid = "0";
        }
        if(currentPage == null || currentPage.length() == 0){
            currentPage = "1";
        }
        if(pageSize == null || pageSize.length() == 0){
            pageSize = "5";
        }
        PageBean pageBean = service.pageQuery(cid, keyWord, currentPage, pageSize);
        writeValue(pageBean, response);
    }

    public void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        Route route = service.findDetail(rid);
        writeValue(route, response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        int uid;
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            uid = 0;
        }else{
            uid = user.getUid();
        }
        writeValue(favoriteService.isFavorite(rid, uid), response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/login.html");
        }else{
            String rid = request.getParameter("rid");
            response.setContentType("application/json;charset=utf-8");
            writeValue(favoriteService.addFavorite(rid, user.getUid()), response);
        }

    }

    public void sortFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String routeName = request.getParameter("routeName");
        String lowPrice = request.getParameter("lowPrice");
        String highPrice = request.getParameter("highPrice");
        if(currentPage == null || currentPage.length() == 0){
            currentPage = "1";
        }
        if(pageSize == null || pageSize.length() == 0){
            pageSize = "8";
        }
        PageBean pageBean = service.favoritePageQuery(currentPage, pageSize, routeName, lowPrice, highPrice);
        writeValue(pageBean, response);
    }

    public void userFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        Object[] obj = new Object[2];
        if(user == null){
            //用户尚未登录
            obj[0] = "html";
        }else{
            obj[0] = "json";
            int uid = user.getUid();
            String currentPage = request.getParameter("currentPage");
            String pageSize = request.getParameter("pageSize");
            if(currentPage == null || currentPage.length() == 0){
                currentPage = "1";
            }
            if(pageSize == null || pageSize.length() == 0){
                pageSize = "12";
            }
            PageBean pageBean = favoriteService.findFavorite(uid, currentPage, pageSize);
            obj[1] = pageBean;
        }
        writeValue(obj, response);
    }

}
