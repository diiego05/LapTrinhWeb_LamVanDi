package vn.iotstar.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.utils.Constant;

public class DownloadImageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String fileName = req.getParameter("fname");

	    // Chỉ lấy tên file, ngăn chặn ../
	    if (fileName != null && !fileName.isEmpty()) {
	        fileName = new File(fileName).getName();
	    }

	    File file = new File(Constant.DIR + "/" + fileName);
	    resp.setContentType("image/jpeg");
	    if (file.exists()) {
	        IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
	    }
	}
}
