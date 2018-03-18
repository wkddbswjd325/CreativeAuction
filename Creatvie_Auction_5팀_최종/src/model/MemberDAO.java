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

public class MemberDAO {
	private DataSource ds;
	public MemberDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	public int create(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO MEMBER VALUES ");
			insertQuery.append("(?, ?, ?, ?, ?, ?, ?)");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, member.getM_id());
			pstmt.setString(2, member.getM_password());
			pstmt.setString(3, member.getM_phone());
			pstmt.setString(4, member.getM_email());		
			pstmt.setString(5, member.getM_address());
			pstmt.setString(6, member.getM_name());
			pstmt.setInt(7, member.getM_score());

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

	/**
	 * ������ ����� ����� ������ ����.
	 */
	public int update(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE MEMBER SET ");
			updateQuery.append("m_password=?, m_phone=?, m_email=?, m_address=?, m_name=? ");
			updateQuery.append("WHERE m_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setString(1, member.getM_password());
			pstmt.setString(2, member.getM_phone());
			pstmt.setString(3, member.getM_email());		
			pstmt.setString(4, member.getM_address());
			pstmt.setString(5, member.getM_name());
			pstmt.setString(6, member.getM_id());
			
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

	/**
	 * ����� ���̵� �ش��ϴ� ����ڸ� ����.
	 */
	public int remove(String m_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM MEMBER ");
			removeQuery.append("WHERE m_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, m_id);
			
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

	public Member findMember(String m_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("m_password, m_phone, m_email, m_address, m_name, m_score ");
			findQuery.append("FROM MEMBER ");
			findQuery.append("WHERE m_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, m_id);
			
			rs = pstmt.executeQuery();
			
			Member member = null;
			if ( rs.next() ){
				member = new Member();
				member.setM_id(m_id);
				member.setM_password(rs.getString("m_password"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_email(rs.getString("m_email"));
				member.setM_address(rs.getString("m_address"));
				member.setM_name(rs.getString("m_name"));
				member.setM_score(rs.getInt("m_score"));
			}
			return member;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	/**
	 * ����� ����Ʈ�� ����� ���� �κ����� ���� �������� 
	 * �������� ī��Ʈ���� �̿��Ͽ� �ش�κ��� ����ڸ��� List�ݷ��ǿ�
	 * �����Ͽ� ��ȯ.
	 */
	public List<Member> findMemberList(int currentPage, int countPerPage)
		throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT m_id, m_password, m_phone, m_email, m_address, m_name, m_score ");
			findQuery.append("FROM MEMBER ORDER BY m_id");
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			List<Member> memberList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				memberList = new ArrayList<Member>();				
				do {
					Member member = new Member();
					member.setM_id(rs.getString("m_id"));
					member.setM_password(rs.getString("m_password"));
					member.setM_phone(rs.getString("m_phone"));	
					member.setM_email(rs.getString("m_email"));
					member.setM_address(rs.getString("m_address"));
					member.setM_name(rs.getString("m_name"));
					member.setM_score(rs.getInt("m_score"));
					memberList.add(member);					
				} while ( (rs.next()) && (--countPerPage > 0));				
			}
			return memberList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	/**
	 * ���ڷ� ���޵Ǵ� ���̵� ������ ����ڰ� �����ϴ����� 
	 * ������ �Ǻ�. 
	 */
	public boolean existedMember(String m_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM MEMBER ");
			existedQuery.append("WHERE m_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(existedQuery.toString());
			pstmt.setString(1, m_id);
			
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
