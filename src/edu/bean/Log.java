package edu.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Log implements Bean<Log>{
	private int logId;
	private int userId;
	private int itemId;
	
	private String logTitle;
	private String logDesc;
	private Date date;
	private int logType;//1提醒 2 备忘
	
	public Log(int logId, int userId, int itemId, String logTitle, String logDesc, Date date, int logType) {
		super();
		this.logId = logId;
		this.userId = userId;
		this.itemId = itemId;
		this.logTitle = logTitle;
		this.logDesc = logDesc;
		this.date = date;
		this.logType = logType;
	}

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", userId=" + userId + ", itemId=" + itemId + ", logTitle=" + logTitle
				+ ", logDesc=" + logDesc + ", date=" + date + ", logType=" + logType + "]";
	}

	@Override
	public Log parseRst(ResultSet rs) throws SQLException {
		this.logId = rs.getInt("log_id");
		this.userId = rs.getInt("user_id");
		this.itemId = rs.getInt("item_id");
		this.logTitle = rs.getString("log_title");
		this.logDesc = rs.getString("log_desc");
		this.date = rs.getDate("date");
		this.logType = rs.getInt("log_type");
		return this;
	}
}
