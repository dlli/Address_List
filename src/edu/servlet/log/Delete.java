package edu.servlet.log;

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
import jdk.nashorn.internal.parser.JSONParser;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/log/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		
		Connection conn = DbUtil.getConnection();
		String sql = "delete from log where log_id=?";
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1,Integer.parseInt(request.getParameter("id")));
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("f") != null && request.getParameter("f").equals("detail")) {
			System.out.println("个人备注删除");
			response.sendRedirect("../contact/Detail?id=" + request.getParameter("detail_id"));
		}else {
			response.sendRedirect("Lst");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
