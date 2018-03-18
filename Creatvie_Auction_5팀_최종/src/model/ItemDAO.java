package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ItemDAO {
	private DataSource ds;
	public ItemDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	/**
	 * 새로운 아이템 생성
	 */
	public int create(Item item) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO ITEM VALUES ");
			insertQuery.append("(seq_item.nextval, ?, ?, ?)");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, item.getM_id());
			pstmt.setString(2, item.getI_img());
			pstmt.setString(3, item.getI_name());		
			
			int result = pstmt.executeUpdate();
			return result;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}


	public int remove(String m_id, String[] i_id) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			con = ds.getConnection();

			for(int i=0; i < i_id.length; i++) {
				StringBuffer removeQuery = new StringBuffer();
				removeQuery.append("DELETE FROM ITEM ");
				removeQuery.append("WHERE m_id = ? and i_id = ? ");		

				pstmt = con.prepareStatement(removeQuery.toString());
				pstmt.setString(1, m_id);
				pstmt.setInt(2, Integer.parseInt(i_id[i]));
				
				result = pstmt.executeUpdate();	
				
			}		
			return result;
			
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}

	
	//사용자에 해당하는 아이템 리스트 가져오기
	public List<Item> findItemList(String m_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT * FROM ITEM ");
			findQuery.append("WHERE m_id=? ");   
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(findQuery.toString());      
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();

			ArrayList<Item> itemList = new ArrayList<Item>();
			while(rs.next()){
				Item item = new Item();
				item.setI_id(rs.getInt("i_id"));
				item.setI_img(rs.getString("i_img"));
				item.setI_name(rs.getString("i_name"));
				itemList.add(item);
			}
			return itemList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}         
			if ( con != null ){
				con.close();
			}
		}
	}


	public boolean existedItemId(int i_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM ITEM ");
			existedQuery.append("WHERE i_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setInt(1, i_id);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			if ( rs.next() ){
				count = rs.getInt(1);
			}
			if ( count == 1 ) {
				return true;
			} else {
				return false;
			}
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}		
	}
}
