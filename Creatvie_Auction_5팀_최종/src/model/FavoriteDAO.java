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

public class FavoriteDAO {
	private DataSource ds;
	public FavoriteDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	

	public int insert(Favorite favorite) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO FAVORITE VALUES ");
			insertQuery.append("(?, ?)");		

			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, favorite.getM_id());
			pstmt.setInt(2, favorite.getA_id());

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

	public boolean existedEquiFavorite(String m_id, int a_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM FAVORITE ");
			existedQuery.append("WHERE m_id=? and a_id=?");		

			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setString(1, m_id);
			pstmt.setInt(2, a_id);

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

	public List<Auction> findFavoriteList(int currentPage, int countPerPage, String m_id)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT * ");	
			findQuery.append("FROM AUCTION A JOIN CATEGORY_SMALL C ON C.SMALL_CATE_ID = A.SMALL_CATE_ID ");
			findQuery.append("JOIN ITEM I ON A.I_ID = I.I_ID ");
			findQuery.append("WHERE A.A_ID IN (SELECT A_ID FROM FAVORITE WHERE M_ID = ?) ORDER BY a_id");

			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );	

			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;

			ArrayList<Auction> favoriteList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				favoriteList = new ArrayList<Auction>();				
				do {
					Auction auction = new Auction();
					auction.setA_id(rs.getInt("a_id"));
					auction.setA_title(rs.getString("a_title"));
					auction.setM_id(rs.getString("m_id"));
					auction.setA_state(rs.getString("a_state"));
					auction.setA_content(rs.getString("a_content"));
					auction.setA_writedate(rs.getDate("a_writedate"));
					auction.setA_deadline(rs.getDate("a_deadline"));
					auction.setI_id(rs.getInt("i_id"));
					auction.setSmall_cate_id(rs.getInt("small_cate_id"));
					auction.setBig_cate_id(rs.getInt("big_cate_id"));			
					auction.setSmall_cate_name(rs.getString("small_cate_name"));
					auction.setI_img(rs.getString("i_img"));
					favoriteList.add(auction);							
				} while ( (rs.next()) && (--countPerPage > 0));				
			}
			return favoriteList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	
	public int remove(String m_id, String[] a_id) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			con = ds.getConnection();

			for(int i=0; i < a_id.length; i++) {
				StringBuffer removeQuery = new StringBuffer();
				removeQuery.append("DELETE FROM FAVORITE ");
				removeQuery.append("WHERE m_id = ? and a_id = ? ");		

				pstmt = con.prepareStatement(removeQuery.toString());
				pstmt.setString(1, m_id);
				pstmt.setInt(2, Integer.parseInt(a_id[i]));
				
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

}

