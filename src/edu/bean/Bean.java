package edu.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Bean<T> {
	
	/**
	 * ×ª»»ResultSet ³É<T>
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public T parseRst(ResultSet rs) throws SQLException;
	
}
