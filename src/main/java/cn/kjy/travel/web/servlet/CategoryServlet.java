package cn.kjy.travel.web.servlet;

import cn.kjy.travel.domain.Category;
import cn.kjy.travel.service.CategoryService;
import cn.kjy.travel.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author kjy
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> categories = service.findAll();
        writeValue(categories, response);
    }
}
