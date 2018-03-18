package model;

import java.sql.SQLException;
import java.util.List;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * auctionDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class AuctionManager {
	private static AuctionManager auctionMan = new AuctionManager();
	private AuctionDAO auctionDAO;

	private AuctionManager() {
		try {
			auctionDAO = new AuctionDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static AuctionManager getInstance() {
		return auctionMan;
	}

	public List<Auction> findAuctionList(int currentPage, int countPerPage, String cate) throws SQLException {
		return auctionDAO.findAuctionList(currentPage, countPerPage, cate);
	}
	//홍채연 추가
	public List<Auction> findMyAuctionList(int currentPage, int countPerPage, String m_id) throws SQLException {
		return auctionDAO.findMyAuctionList(currentPage, countPerPage, m_id);
	}
	
	public List<Auction> searchList_title(int currentPage, int countPerPage, String search) throws SQLException {
		return auctionDAO.searchList_title(currentPage, countPerPage, search);
	}
	
	public List<Auction> searchList_writer(int currentPage, int countPerPage, String search) throws SQLException {
		return auctionDAO.searchList_writer(currentPage, countPerPage, search);
	}
	
	public AuctionDAO getauctionDAO() {
		return this.auctionDAO;
	}
	
	public int create(Auction auction) throws SQLException, AlreadyUsedItemException {
		if (auctionDAO.alreadyUsedItemException(auction.getI_id())) {
			throw new AlreadyUsedItemException("이미 사용된 아이템입니다");
		}
		return auctionDAO.create(auction);
	}
	
	public int remove(String a_id) throws SQLException {
		return auctionDAO.remove(a_id);
	}
	
	public int updateState(String a_id) throws SQLException {
		return auctionDAO.updateState(a_id);
	}
	
	public Auction viewAuction(int a_id)
			throws SQLException {
			Auction auction = auctionDAO.viewAuction(a_id);
	
			return auction;
	}
	public int findAId(Auction auction) throws SQLException {
		return auctionDAO.findAId(auction);
	}

}
