package edu.servlet.users;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import edu.dao.DbUtil;

/**
 * Servlet implementation class Reg
 */
@WebServlet("/users/Reg")
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");  
		request.getRequestDispatcher("/users/reg.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		
		Connection conn = DbUtil.getConnection();
		String sql = "insert into users(login_name,login_pwd) values(?,?)";
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,request.getParameter("login_name"));
			ptmt.setString(2,request.getParameter("login_pwd"));
			ptmt.executeUpdate();
			request.setAttribute("login_name", request.getParameter("login_name"));
			request.setAttribute("login_pwd", request.getParameter("login_pwd"));
			request.getRequestDispatcher("/users/reg_success.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "用户名已存在");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			//e.printStackTrace();
		}
	}

}
