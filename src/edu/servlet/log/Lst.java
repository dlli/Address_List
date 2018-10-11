package edu.servlet.log;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import edu.bean.ContactsItem;
import edu.bean.Log;
import edu.bean.LogContactBean;
import edu.bean.Users;
import edu.dao.DbUtil;

/**
 * Servlet implementation class Lst
 */
@WebServlet("/log/Lst")
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
		System.out.println("获取用户备忘录列表");
		if(user == null) {
			request.setAttribute("error", "未登陆");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		String d = request.getParameter("d");
		Date date = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		try {
			date = sf.parse(d);
			System.out.println(date.toString());
		}catch(Exception e){
			date = new Date();
			System.out.println(date.toString());
		}
		String dateQueryStr = sf.format(date);
		int month = date.getMonth();
		request.setAttribute("y", 1900+date.getYear());
		request.setAttribute("m", month);
		request.setAttribute("d", dateQueryStr);
		Calendar c = Calendar.getInstance();
		c.set(1990+date.getYear(), 1+date.getMonth(),0); 
		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("当前月共：" + maxDay + "天");
		request.setAttribute("day", maxDay);
		month = month+1;
		String dateQueryStr2 = "";
		if(month < 10) {
			 dateQueryStr2 = "%-0" + month + "-%";
		}else {
			 dateQueryStr2= "%-" + month +"-%";
		}
		
		System.out.println("月份：" + month);
		dateQueryStr = "%" + dateQueryStr + "%"; 
		System.out.println("查询字符串" + dateQueryStr + "dateQueryStr2:" + dateQueryStr2);
		// 获取指定月份的该用户所有 备忘录
		String sql = "select log.*,contacts_item.item_name from log left join contacts_item on contacts_item.item_id=log.item_id where log.user_id=? and( (date like ?) or (log_type=3 and date like ?) )";
		
		Connection conn = DbUtil.getConnection();
		
		ArrayList<LogContactBean> lst = new ArrayList<LogContactBean>();
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sql);
			//设置参数
			ptmt.setInt(1, user.getUserId());
			ptmt.setString(2, dateQueryStr);
			ptmt.setString(3, dateQueryStr2);
			
			
			//获取结果集
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				lst.add(new LogContactBean().parseRst(rs));
			}
			System.out.println("共计发现备忘录：" + lst.size()+"个");
			
			Map<Integer,ArrayList<LogContactBean>> m = new HashMap<Integer,ArrayList<LogContactBean>>();
			
			for(int x = 0;x<lst.size();x++) {
				LogContactBean temp = lst.get(x);
				int day = temp.getDate().getDate();
				ArrayList<LogContactBean> tempLst = (m.get(day) == null)?new ArrayList<LogContactBean>():m.get(day);
				tempLst.add(temp);
				m.put(day, tempLst);
			}
			request.setAttribute("map",m);
			
			request.getRequestDispatcher("/log/lst.jsp").forward(request, response);
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
		doGet(request,response);
	}
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(2018, 2,0);
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

}
