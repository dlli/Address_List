package edu.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsItem implements Bean<ContactsItem>{
	
	private int itemId;
	private int userId;
	private String itemName;
	private String itemAlias;
	private Date itemBirthday;
	private String itemAddr;
	private String itemTel;

	
	
	
	
	public ContactsItem(int itemId, int userId, String itemName, String itemAlias, Date itemBirthday, String itemAddr,
			String itemTel) {
		super();
		this.itemId = itemId;
		this.userId = userId;
		this.itemName = itemName;
		this.itemAlias = itemAlias;
		this.itemBirthday = itemBirthday;
		this.itemAddr = itemAddr;
		this.itemTel = itemTel;
	}





	public int getItemId() {
		return itemId;
	}





	public void setItemId(int itemId) {
		this.itemId = itemId;
	}





	public int getUserId() {
		return userId;
	}





	public void setUserId(int userId) {
		this.userId = userId;
	}





	public String getItemName() {
		return itemName;
	}





	public void setItemName(String itemName) {
		this.itemName = itemName;
	}





	public String getItemAlias() {
		return itemAlias;
	}





	public void setItemAlias(String itemAlias) {
		this.itemAlias = itemAlias;
	}





	public Date getItemBirthday() {
		return itemBirthday;
	}





	public void setItemBirthday(Date itemBirthday) {
		this.itemBirthday = itemBirthday;
	}





	public String getItemAddr() {
		return itemAddr;
	}





	public void setItemAddr(String itemAddr) {
		this.itemAddr = itemAddr;
	}





	public String getItemTel() {
		return itemTel;
	}





	public void setItemTel(String itemTel) {
		this.itemTel = itemTel;
	}





	public ContactsItem() {
		super();
		// TODO Auto-generated constructor stub
	}





	@Override
	public String toString() {
		return "ContactsItem [itemId=" + itemId + ", userId=" + userId + ", itemName=" + itemName + ", itemAlias="
				+ itemAlias + ", itemBirthday=" + itemBirthday + ", itemAddr=" + itemAddr + ", itemTel=" + itemTel
				+ "]";
	}





	@Override
	public ContactsItem parseRst(ResultSet rs) throws SQLException {

		this.itemId = rs.getInt("item_id");
		this.userId = rs.getInt("user_id");
		this.itemName = rs.getString("item_name");
		this.itemAlias = rs.getString("item_alias");
		this.itemBirthday = rs.getDate("item_birthday");
		this.itemAddr = rs.getString("item_addr");
		this.itemTel = rs.getString("item_tel");
		
		return this;
	}
	
}
