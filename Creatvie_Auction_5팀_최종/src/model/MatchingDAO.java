package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MatchingDAO {
	private DataSource ds;
	public MatchingDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	

	@SuppressWarnings("resource")
	public int insert(Matching matching) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		try {
			con = ds.getConnection();
			
			StringBuffer findQuery1 = new StringBuffer();
			findQuery1.append("SELECT I_ID FROM AUCTION WHERE A_ID="+matching.getA_id());
			pstmt = con.prepareStatement(findQuery1.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );	
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				matching.setA_item_id(rs.getInt("i_id"));
			}
			
			StringBuffer findQuery2 = new StringBuffer();
			findQuery2.append("SELECT I_ID, M_ID FROM REPLY WHERE R_ID="+matching.getR_id());
			pstmt = con.prepareStatement(findQuery2.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );	
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				matching.setR_item_id(rs.getInt("i_id"));
				matching.setR_mem_id(rs.getString("M_id"));
			}	
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO MATCHING VALUES ");
			insertQuery.append("(?, ?, ?, ?, ?, ?)");		

			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setInt(1, matching.getA_id());
			pstmt.setInt(2, matching.getR_id());
			pstmt.setString(3, matching.getA_mem_id());
			pstmt.setInt(4, matching.getA_item_id());
			pstmt.setString(5, matching.getR_mem_id());
			pstmt.setInt(6, matching.getR_item_id());

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

	public boolean alreadyMatchingExceiption(int a_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM MATCHING ");
			existedQuery.append("WHERE a_id=?");		

			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setInt(1, a_id);

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
	
	@SuppressWarnings("resource")
	public Matching viewMatching (int a_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT * FROM MATCHING WHERE A_ID = ?");
		
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setInt(1, a_id);
			rs = pstmt.executeQuery();
			
			Matching matching = null;
			
			if ( rs.next() ){
				matching = new Matching();
				matching.setA_id(rs.getInt("a_id"));
				matching.setR_id(rs.getInt("r_id"));
				matching.setA_mem_id(rs.getString("a_mem_id"));
				matching.setA_item_id(rs.getInt("a_item_id"));
				matching.setR_mem_id(rs.getString("r_mem_id"));
				matching.setR_item_id(rs.getInt("r_item_id"));
			}
			
			findQuery = new StringBuffer();
			findQuery.append("SELECT I_IMG FROM AUCTION A JOIN ITEM I ON A.I_ID = I.I_ID WHERE A_ID = ?");
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setInt(1, a_id);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ){
				matching.setA_item_img(rs.getString("i_img"));
			}
			
			findQuery = new StringBuffer();
			findQuery.append("SELECT m_address, m_phone FROM MEMBER WHERE M_ID = ?");
		
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, matching.getA_mem_id());
			rs = pstmt.executeQuery();
			
			if ( rs.next() ){
				matching.setA_mem_address(rs.getString("m_address"));
				matching.setA_mem_phone(rs.getString("m_phone"));
			}
			
			findQuery = new StringBuffer();
			findQuery.append("SELECT I_IMG FROM REPLY R JOIN ITEM I ON R.I_ID = I.I_ID WHERE R_ID = ?");
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setInt(1, matching.getR_id());
			rs = pstmt.executeQuery();
			
			if ( rs.next() ){
				matching.setR_item_img(rs.getString("i_img"));
			}
			
			findQuery = new StringBuffer();
			findQuery.append("SELECT m_address, m_phone FROM MEMBER WHERE M_ID = ?");
		
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, matching.getR_mem_id());
			rs = pstmt.executeQuery();
			
			if ( rs.next() ){
				matching.setR_mem_address(rs.getString("m_address"));
				matching.setR_mem_phone(rs.getString("m_phone"));
			}
			
			return matching;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}	
	
	public boolean notMatchedIdException(int a_id, String m_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT a_mem_id, r_mem_id FROM MATCHING ");
			existedQuery.append("WHERE a_id=?");		

			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setInt(1, a_id);

			rs = pstmt.executeQuery();
			
			String a_mem_id = null;
			String r_mem_id = null;
			
			if ( rs.next() ){
				a_mem_id = rs.getString("a_mem_id");
				r_mem_id = rs.getString("r_mem_id");
			}
			
			if (a_mem_id.equals(m_id) || r_mem_id.equals(m_id)) {
				return false;
			} else {
				return true;
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


	
