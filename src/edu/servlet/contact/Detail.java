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
import edu.bean.Log;
import edu.bean.Users;
import edu.dao.DbUtil;

/**
 * Servlet implementation class Lst
 */
@WebServlet("/contact/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Detail() {
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
		System.out.println("获取联系人详情");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		Connection conn = DbUtil.getConnection();
		//获取联系人详情
		String sql = "select * from contacts_item where item_id=?";
		ArrayList<Log> lst = new ArrayList<Log>();
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sql);
			//设置参数
			ptmt.setInt(1, Integer.parseInt(request.getParameter("id")));
			//获取结果集
			ResultSet rs = ptmt.executeQuery();
			ContactsItem item = new ContactsItem();
			while(rs.next()) {
				item.parseRst(rs);
			}
			System.out.println("联系人信息：" + item.toString());
			//获取联系人备注
			sql = "select * from log where item_id=?";
			ptmt = conn.prepareStatement(sql);
			//设置参数
			ptmt.setInt(1, Integer.parseInt(request.getParameter("id")));
			rs = ptmt.executeQuery();
			while(rs.next()) {
				lst.add(new Log().parseRst(rs));
			}
			System.out.println("联系人下备注数：" + lst.size());
			
			request.setAttribute("item",item);
			request.setAttribute("lst",lst);
			request.getRequestDispatcher("/contact/detail.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
