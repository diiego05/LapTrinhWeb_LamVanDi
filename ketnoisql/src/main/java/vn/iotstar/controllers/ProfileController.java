package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
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

import vn.iotstar.models.User; // Thay thế bằng Model User của bạn
 // Thay thế bằng Service User của bạn
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl; // Thay thế bằng Service User của bạn
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/member/profile" })
public class ProfileController extends HttpServlet {
    UserService userService = new UserServiceImpl();

    // HIỂN THỊ TRANG PROFILE
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/profile.jsp");
        dispatcher.forward(req, resp);
    }

    // XỬ LÝ CẬP NHẬT PROFILE
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User oldUser = (User) session.getAttribute("acc"); // Lấy user hiện tại từ session
        User updatedUser = new User();

        // Giữ lại các giá trị cũ
        updatedUser.setId(oldUser.getId());
        updatedUser.setUserName(oldUser.getUserName());
        updatedUser.setAvatar(oldUser.getAvatar());

        DiskFileItemFactory diskFileItemFactory = DiskFileItemFactory.builder().get();
		JakartaServletFileUpload servletFileUpload = new JakartaServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (item.getFieldName().equals("fullname")) {
                    updatedUser.setFullName(item.getString());
                } else if (item.getFieldName().equals("phone")) {
                    updatedUser.setPhone(item.getString());
                } else if (item.getFieldName().equals("avatar")) {
                    // Nếu có upload file mới
                    if (item.getSize() > 0) {
                        String originalFileName = item.getName();
						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);
						String fileName = System.currentTimeMillis() + "." + ext;
						File file = new File(Constant.DIR + "/avatar/" + fileName); // Lưu vào thư mục avatar
						item.write(file.toPath());
						updatedUser.setAvatar("avatar/" + fileName);
                    }
                }
            }

            // Gọi service để cập nhật vào DB
            UserService.update(updatedUser);

            // Cập nhật lại thông tin trong session
            session.setAttribute("acc", updatedUser);

            resp.sendRedirect(req.getContextPath() + "/member/profile");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}