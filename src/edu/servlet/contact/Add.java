package edu.servlet.contact;

import java.io.IOException;
import java.sql.PreparedStatement;
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

import edu.bean.Users;
import edu.dao.DbUtil;

/**
 * Servlet implementation class Add
 */
@WebServlet("/contact/Add")
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
		request.getRequestDispatcher("/contact/add.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		
		
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("新增联系人");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		Connection conn = DbUtil.getConnection();
		String sql = "insert into contacts_item(user_id,item_name,item_alias,item_birthday,item_addr,item_tel) values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1,user.getUserId());
			ptmt.setString(2,request.getParameter("item_name"));
			ptmt.setString(3,request.getParameter("item_alias"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String birthday = request.getParameter("item_birthday");
			System.out.println("生日：" + birthday);
			Date date = sdf.parse(request.getParameter("item_birthday"));
			ptmt.setDate(4,new java.sql.Date(date.getTime()));
			ptmt.setString(5,request.getParameter("item_addr"));
			ptmt.setString(6,request.getParameter("item_tel"));
			ptmt.executeUpdate();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.sendRedirect("Lst");
	}

}
