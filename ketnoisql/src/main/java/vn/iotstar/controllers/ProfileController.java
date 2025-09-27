package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/member/profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/profile.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User oldUser = (User) session.getAttribute("account");
        if (oldUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User updatedUser = new User();
        updatedUser.setId(oldUser.getId());
        updatedUser.setUserName(oldUser.getUserName());
        updatedUser.setAvatar(oldUser.getAvatar()); // giữ avatar cũ nếu không upload mới

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String value = item.getString(StandardCharsets.UTF_8);
                    if ("fullname".equals(item.getFieldName())) {
                        updatedUser.setFullName(value);
                    } else if ("phone".equals(item.getFieldName())) {
                        updatedUser.setPhone(value);
                    }
                } else if ("avatar".equals(item.getFieldName())) {
                    if (item.getSize() > 0) {
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = (index > 0) ? originalFileName.substring(index) : ".jpg";
                        String fileName = System.currentTimeMillis() + ext;

                        // Lưu file vào thư mục Constant.DIR/avatar
                        File uploadDir = new File(Constant.DIR + "/avatar");
                        if (!uploadDir.exists()) uploadDir.mkdirs();

                        File file = new File(uploadDir, fileName);
                        item.write(file.toPath());

                        // ❌ KHÔNG lưu "uploads/avatar/..."
                        // ✅ Chỉ lưu "avatar/..."
                        updatedUser.setAvatar("avatar/" + fileName);
                    }
                }
            }

            boolean success = userService.update(updatedUser);
            if (success) {
                session.setAttribute("account", updatedUser);
            }

            resp.sendRedirect(req.getContextPath() + "/member/profile");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra khi cập nhật hồ sơ: " + e.getMessage());
            doGet(req, resp);
        }
    }
}
