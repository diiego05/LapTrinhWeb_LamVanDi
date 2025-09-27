package vn.iotstar.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.utils.Constant;

@WebServlet("/image")
public class DownloadImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String filePath = req.getParameter("fname"); // VD: uploads/avatar/123.png

        if (filePath != null && !filePath.isEmpty()) {
            filePath = filePath.replace("..", ""); // chống ../

            // Ghép vào thư mục gốc (Constant.DIR trỏ tới webapp/uploads)
            File file = new File(getServletContext().getRealPath("") + File.separator + filePath);

            if (file.exists()) {
                // đoán mime type
                String mimeType = getServletContext().getMimeType(file.getName());
                if (mimeType == null) mimeType = "application/octet-stream";

                resp.setContentType(mimeType);
                resp.setContentLengthLong(file.length());

                try (FileInputStream fis = new FileInputStream(file);
                     OutputStream os = resp.getOutputStream()) {
                    fis.transferTo(os);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + filePath);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File name is missing!");
        }
    }
}
