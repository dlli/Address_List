package edu.servlet.log;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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

import edu.bean.Users;
import edu.dao.DbUtil;

/**
 * Servlet implementation class Add
 */
@WebServlet("/log/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
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
		request.getRequestDispatcher("/log/add.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		
		
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("新增备忘");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "insert into log(user_id,item_id,log_title,log_desc,date,log_type) values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1,user.getUserId());
			if(request.getParameter("item_id").equals("0")) {
				ptmt.setNull(2, Types.INTEGER);
			}else {
				ptmt.setInt(2, Integer.parseInt(request.getParameter("item_id")));
			}
			ptmt.setString(3,request.getParameter("log_title"));
			ptmt.setNull(2, Types.INTEGER);
			ptmt.setString(4,request.getParameter("log_desc"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(request.getParameter("date"));
			
			ptmt.setDate(5,new java.sql.Date(date.getTime()));
			ptmt.setInt(6, Integer.parseInt(request.getParameter("log_type")));
			ptmt.executeUpdate();
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(request.getParameter("item_id").equals("0")) {
			response.sendRedirect("../log/Lst");
		}else {
			response.sendRedirect("../contact/Detail?id=" + request.getParameter("item_id"));
		}
		
		
	}

}
