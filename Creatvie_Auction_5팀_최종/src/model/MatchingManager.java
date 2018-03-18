package model;

import java.sql.SQLException;

public class MatchingManager {
	private static MatchingManager matchingMan = new MatchingManager();
	private MatchingDAO matchingDAO;

	private MatchingManager() {
		try {
			matchingDAO = new MatchingDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static MatchingManager getInstance() {
		return matchingMan;
	}
	
	public int insert(Matching matching) throws SQLException, AlreadyMatchingException {
		if (matchingDAO.alreadyMatchingExceiption(matching.getA_id())) {
			throw new AlreadyMatchingException("�̹� ��Ī�� ���Դϴ�");
		}
		return matchingDAO.insert(matching);
	}
	
	public Matching viewMatching(int a_id, String m_id) throws SQLException, NotMatchedIdException {
		if(matchingDAO.notMatchedIdException(a_id, m_id)) {
			throw new NotMatchedIdException("��Ī�� ȸ���� ��ȸ�� �����մϴ�");
		}
		return matchingDAO.viewMatching(a_id);
	}
	
//	public int remove(String m_id, String[] a_id) throws SQLException {
//		return favoriteDAO.remove(m_id, a_id);
//	}
//	
//	public List<Auction> findFavoriteList(int currentPage, int countPerPage, String m_id) throws SQLException {
//		return favoriteDAO.findFavoriteList(currentPage, countPerPage, m_id);
//	}

}

