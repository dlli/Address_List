package edu.servlet.log;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import edu.bean.ContactsItem;
import edu.bean.Log;
import edu.bean.Users;
import edu.dao.DbUtil;

/**
 * Servlet implementation class Add
 */
@WebServlet("/log/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
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
		
		Users user = (Users) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("编辑备注 ID:" + id);
		
		Connection conn = DbUtil.getConnection();
		String sql = "select * from log where log_id=?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1,id);;
			ResultSet rs = ptmt.executeQuery();
			Log item = new Log();
			while(rs.next()) {
				item.parseRst(rs);
			}
			System.out.println("编辑备注信息："+item.toString());
			request.setAttribute("item", item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/log/edit.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		
		
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("编辑备忘录");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		Connection conn = DbUtil.getConnection();
		String sql = "update log set log_title=?,log_desc=?,date=?,log_type=? where log_id=?";
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,request.getParameter("log_title"));
			ptmt.setString(2,request.getParameter("log_desc"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(request.getParameter("date"));
			ptmt.setDate(3,new java.sql.Date(date.getTime()));
			ptmt.setString(4,request.getParameter("log_type"));
			
			ptmt.setInt(5, Integer.parseInt(request.getParameter("log_id")));
			ptmt.executeUpdate();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("f:" + request.getParameter("f"));
		if(request.getParameter("f") != null && request.getParameter("f").equals("detail")) {
			System.out.println("个人备忘录编辑");
			response.sendRedirect("../contact/Detail?id=" + request.getParameter("detail_id"));
		}else {
			response.sendRedirect("Lst");
		}
	}

}
