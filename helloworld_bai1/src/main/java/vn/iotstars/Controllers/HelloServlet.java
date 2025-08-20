package vn.iotstars.Controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.Cookie;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(urlPatterns= {"/hello","/xin-chao"})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public HelloServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		String name=
		"";
		//Nhận cookie
		Cookie[] cookie = request.getCookies();
		for (Cookie c: cookie) {
		if(c.getName().equals("username")) {
		name = c.getValue();}}
		if(name.equals("")){
		//chuyển sang trang LoginServlet
		response.sendRedirect("/HelloServlet/login");
		}
		//hiển thị lên trang bằng đối tượng PrintWriter()
		printWriter.println("Xin chao " + name);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
