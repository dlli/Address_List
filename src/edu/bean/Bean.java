package edu.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Bean<T> {
	
	/**
	 * ת��ResultSet ��<T>
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public T parseRst(ResultSet rs) throws SQLException;
	
}
