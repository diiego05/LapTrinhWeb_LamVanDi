package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.dao.CategoryDAO;
import vn.iotstar.dao.impl.CategoryDAOImpl;
import vn.iotstar.models.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/admin/category/list"})
public class CategoryeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    CategoryDAO cateDao = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> cateList = cateDao.getAll(); // <-- kiểm tra chỗ này có trả dữ liệu ko
        req.setAttribute("cateList", cateList);
        req.getRequestDispatcher("/view/list-category.jsp").forward(req, resp);
    }
}
