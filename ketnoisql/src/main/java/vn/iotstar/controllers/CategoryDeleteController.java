package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            if (id != null) {
                int cateId = Integer.parseInt(id);

                // lấy category để xóa ảnh luôn
                Category cate = cateService.get(cateId);
                if (cate != null) {
                    // nếu có icon thì xóa file trong uploads
                    String iconPath = cate.getIcon();
                    if (iconPath != null && !iconPath.isEmpty()) {
                        File file = new File(getServletContext().getRealPath("")
                                + File.separator + iconPath);
                        if (file.exists()) {
                            file.delete();
                        }
                    }

                    // xóa trong DB
                    cateService.delete(cateId);
                }
            }

            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/category/list?error=deleteFailed");
        }
    }
}
