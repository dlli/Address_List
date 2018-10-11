package edu.servlet.contact;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import edu.bean.ContactsItem;
import edu.bean.Users;
import edu.dao.DbUtil;

/**
 * Servlet implementation class Lst
 */
@WebServlet("/contact/Lst")
public class Lst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lst() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");  response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("获取用户联系人列表");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		Connection conn = DbUtil.getConnection();
		//SQL 获取学生的所有作业
		String sql = "select * from contacts_item where user_id=?";
		ArrayList<ContactsItem> lst = new ArrayList<ContactsItem>();
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sql);
			//设置参数
			ptmt.setInt(1, user.getUserId());
			//获取结果集
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				lst.add(new ContactsItem().parseRst(rs));
			}
			System.out.println("共计发现联系人：" + lst.size()+"个");
			request.setAttribute("lst",lst);
			request.getRequestDispatcher("/contact/lst.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		String key = request.getParameter("k");
		key = "%" + key + "%";
		
		System.out.println("联系人关键字模糊查询：" + key);
		
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("获取用户联系人列表");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		Connection conn = DbUtil.getConnection();
		//SQL 获取学生的所有作业
		String sql = "select * from contacts_item where user_id=? and (item_name like ? or item_alias like ?)";
		ArrayList<ContactsItem> lst = new ArrayList<ContactsItem>();
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sql);
			//设置参数
			ptmt.setInt(1, user.getUserId());
			ptmt.setString(2, key);
			ptmt.setString(3, key);
			//获取结果集
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				lst.add(new ContactsItem().parseRst(rs));
			}
			System.out.println("共计发现联系人：" + lst.size()+"个");
			request.setAttribute("lst",lst);
			request.setAttribute("k", request.getAttribute("k"));
			request.getRequestDispatcher("/contact/lst.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
