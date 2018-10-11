package edu.servlet.users;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import edu.bean.Users;
import edu.dao.DbUtil;

/**
 * Servlet implementation class Reg
 */
@WebServlet("/users/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		request.getRequestDispatcher("/users/login.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		
		Connection conn = DbUtil.getConnection();
		String sql = "select * from users where login_name=?";
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,request.getParameter("login_name"));
			ResultSet rs = ptmt.executeQuery();
			Users user = new Users();
			while(rs.next()) {
				user.parseRst(rs);
			}
			
			if(user.getLoginPwd().equals( request.getParameter("login_pwd"))) {
				//登陆成功
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				System.out.println("登陆成功");
				response.sendRedirect("../contact/Lst");
			}else {
				request.setAttribute("error", "登陆失败");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "用户名已存在");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			//e.printStackTrace();
		} catch(NullPointerException e) {
			request.setAttribute("error", "用户不存�?");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
