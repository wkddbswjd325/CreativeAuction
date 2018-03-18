package model;

import java.sql.SQLException;
import java.util.List;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * auctionDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
	//ȫä�� �߰�
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
			throw new AlreadyUsedItemException("�̹� ���� �������Դϴ�");
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
