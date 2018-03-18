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

public class AuctionDAO {
	private DataSource ds;
	
	public AuctionDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}
	
	public List<Auction> findAuctionList(int currentPage, int countPerPage, String cate)
		throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT * ");
			if(cate == null) {
				findQuery.append("FROM AUCTION A JOIN CATEGORY_SMALL C ON C.SMALL_CATE_ID = A.SMALL_CATE_ID ");
				findQuery.append("JOIN ITEM I ON A.I_ID = I.I_ID ORDER BY a_id desc");
			} else {
				int category = Integer.parseInt(cate);
				if(category <= 12) {
					findQuery.append("FROM AUCTION A JOIN CATEGORY_SMALL C ON C.SMALL_CATE_ID = A.SMALL_CATE_ID ");
					findQuery.append("JOIN ITEM I ON A.I_ID = I.I_ID WHERE A.BIG_CATE_ID=" + category + " ORDER BY a_id desc");
				}
				else {
					findQuery.append("FROM AUCTION A JOIN CATEGORY_SMALL C ON C.SMALL_CATE_ID = A.SMALL_CATE_ID ");
					findQuery.append("JOIN ITEM I ON A.I_ID = I.I_ID WHERE A.SMALL_CATE_ID=" + category + " ORDER BY a_id desc");
				}
			}
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			ArrayList<Auction> auctionList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				auctionList = new ArrayList<Auction>();				
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
					auctionList.add(auction);							
				} while ( (rs.next()) && (--countPerPage > 0));				
			}
			return auctionList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	//홍채연 추가
	public List<Auction> findMyAuctionList(int currentPage, int countPerPage, String m_id)
			throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				StringBuffer findQuery = new StringBuffer();
				findQuery.append("SELECT * ");
				findQuery.append("FROM AUCTION A ");
				findQuery.append("WHERE A.A_ID IN ");
				findQuery.append("(SELECT DISTINCT MAT.A_ID ");
				findQuery.append("FROM MATCHING MAT ");
				findQuery.append("WHERE MAT.A_MEM_ID=? OR MAT.R_MEM_ID=? ");
				findQuery.append("GROUP BY MAT.A_ID) ");
				findQuery.append("ORDER BY A.A_ID desc");
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
				pstmt.setString(1, m_id);
				pstmt.setString(2, m_id);
				rs = pstmt.executeQuery();

				int start = ((currentPage-1) * countPerPage) + 1;
				
				ArrayList<Auction> myAuctionList = null;
				if ( (start >= 0) && rs.absolute(start) ) {
					myAuctionList = new ArrayList<Auction>();				
					do {
						Auction auction = new Auction();
						auction.setA_id(rs.getInt("a_id"));
						auction.setA_title(rs.getString("a_title"));
						auction.setM_id(rs.getString("m_id"));
						auction.setA_state(rs.getString("a_state"));
						auction.setA_content(rs.getString("a_content"));
						auction.setA_writedate(rs.getDate("a_writedate"));
						auction.setA_deadline(rs.getDate("a_deadline"));
						myAuctionList.add(auction);							
					} while ( (rs.next()) && (--countPerPage > 0));				
				}
				return myAuctionList;
			} finally {
				if ( pstmt != null ){
					pstmt.close();
				}			
				if ( con != null ){
					con.close();
				}
			}
		}
	
	public int create(Auction auction) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AUCTION VALUES ");
			insertQuery.append("(seq_auction.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, auction.getA_title());
			pstmt.setString(2, auction.getM_id());
			pstmt.setString(3, auction.getA_state());
			pstmt.setString(4, auction.getA_content());
			pstmt.setDate(5, auction.getA_writedate());
			pstmt.setDate(6, auction.getA_deadline());
			pstmt.setInt(7, auction.getI_id());
			pstmt.setInt(8, auction.getSmall_cate_id());
			pstmt.setInt(9, auction.getBig_cate_id());

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

	public List<Auction> searchList_title(int currentPage, int countPerPage, String search) 
		throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer searchQuery = new StringBuffer();
			searchQuery.append("SELECT * ");
			searchQuery.append("FROM AUCTION A JOIN CATEGORY_SMALL C ON C.SMALL_CATE_ID = A.SMALL_CATE_ID ");
			searchQuery.append("JOIN ITEM I ON A.I_ID = I.I_ID WHERE A.A_TITLE LIKE '%"+search+"%' ORDER BY a_id desc");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(searchQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );	
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			ArrayList<Auction> auctionList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				auctionList = new ArrayList<Auction>();				
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
					auctionList.add(auction);							
				} while ( (rs.next()) && (--countPerPage > 0));				
			}
			return auctionList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}

	public List<Auction> searchList_writer(int currentPage, int countPerPage, String search) 
		throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer searchQuery = new StringBuffer();
			searchQuery.append("SELECT * ");
			searchQuery.append("FROM AUCTION A JOIN CATEGORY_SMALL C ON C.SMALL_CATE_ID = A.SMALL_CATE_ID ");
			searchQuery.append("JOIN ITEM I ON A.I_ID = I.I_ID WHERE A.M_ID LIKE '%"+search+"%' ORDER BY a_id desc");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(searchQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );		
			rs = pstmt.executeQuery();

			int start = ((currentPage-1) * countPerPage) + 1;
			
			ArrayList<Auction> auctionList = null;
			if ( (start >= 0) && rs.absolute(start) ) {
				auctionList = new ArrayList<Auction>();				
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
					auctionList.add(auction);							
				} while ( (rs.next()) && (--countPerPage > 0));				
			}
			return auctionList;
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
	public int remove(String a_id) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM REPLY ");
			removeQuery.append("WHERE a_id=? ");		
		
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, a_id);
			pstmt.executeUpdate();	
			
			removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM AUCTION ");
			removeQuery.append("WHERE a_id=? ");		

			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setString(1, a_id);
			
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
	
	public Auction viewAuction(int a_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT * ");
			findQuery.append("FROM AUCTION A JOIN CATEGORY_SMALL C ON C.SMALL_CATE_ID = A.SMALL_CATE_ID ");
			findQuery.append("JOIN ITEM I ON A.I_ID = I.I_ID WHERE A_ID=?");
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setInt(1, a_id);
			
			rs = pstmt.executeQuery();
			
			Auction auction = null;
			if ( rs.next() ){
				auction = new Auction();
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
			}
			return auction;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public int updateState(String a_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE AUCTION SET A_STATE='true' WHERE a_id=? ");		

			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setString(1, a_id);
			
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
	
	//a_id(sequence) 가져오기
	public int findAId(Auction auction) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT A_ID ");
			findQuery.append("FROM AUCTION WHERE A_TITLE=? AND M_ID=? AND I_ID=?");
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, auction.getA_title());
			pstmt.setString(2, auction.getM_id());
			pstmt.setInt(3, auction.getI_id());
			
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				return rs.getInt("a_id");
			}
			
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
		return 0;
	}

}
