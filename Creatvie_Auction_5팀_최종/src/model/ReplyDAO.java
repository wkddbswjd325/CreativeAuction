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

/**
 * 사용자 관리에서 데이터베이스와의 작업을 전담하는 클래스.
  추가
 */
public class ReplyDAO {
	private DataSource ds;
	
	public ReplyDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(Reply reply) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO Reply VALUES ");
			insertQuery.append("(?, ?, seq_reply.nextval, ?, ?, ?)");	
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setInt(1, reply.getA_id());
			pstmt.setInt(2, reply.getI_id());;
			pstmt.setString(3, reply.getR_comment());
			pstmt.setDate(4, reply.getR_date());
			pstmt.setString(5, reply.getM_id());

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
	
	@SuppressWarnings("resource")
	public boolean alreadyUsedItemException(int i_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM AUCTION ");
			existedQuery.append("WHERE i_id=?");		

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
				existedQuery = new StringBuffer();
				existedQuery.append("SELECT count(*) FROM REPLY ");
				existedQuery.append("WHERE i_id=?");		

				pstmt = con.prepareStatement(existedQuery.toString());
				pstmt.setInt(1, i_id);

				rs = pstmt.executeQuery();

				count = 0;
				if ( rs.next() ){
					count = rs.getInt(1);
				}
				if ( count == 1 ) {
					return true;
				} else {
					return false;
				}
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

	public List<Reply> findReplyList(int currentPage, int countPerPage, int a_id)
		throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT * ");
			findQuery.append("FROM REPLY R JOIN ITEM I ON R.I_ID = I.I_ID WHERE a_id = ? ORDER BY r_id");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );
			pstmt.setInt(1, a_id);
			
			rs = pstmt.executeQuery();
			
			int start = ((currentPage-1) * countPerPage) + 1;
			
			ArrayList<Reply> replyList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				replyList = new ArrayList<Reply>();				
				do {
					Reply reply = new Reply();
					reply.setA_id(rs.getInt("a_id"));
					reply.setI_id(rs.getInt("i_id"));
					reply.setR_id(rs.getInt("r_id"));
					reply.setR_comment(rs.getString("r_comment"));
					reply.setR_date(rs.getDate("r_date"));
					reply.setM_id(rs.getString("m_id"));
					reply.setI_img(rs.getString("i_img"));
					replyList.add(reply);					
				} while ( (rs.next()) && (--countPerPage > 0));				
			}
			return replyList;
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
