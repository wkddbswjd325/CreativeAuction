package model;

import java.sql.SQLException;
import java.util.List;

public class FavoriteManager {
	private static FavoriteManager favoriteMan = new FavoriteManager();
	private FavoriteDAO favoriteDAO;

	private FavoriteManager() {
		try {
			favoriteDAO = new FavoriteDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static FavoriteManager getInstance() {
		return favoriteMan;
	}
	
	public int insert(Favorite favorite) throws SQLException, ExistedFavoriteException {
		if (favoriteDAO.existedEquiFavorite(favorite.getM_id(), favorite.getA_id())) {
			throw new ExistedFavoriteException("이미 보관함에 담긴 글입니다");
		}
		return favoriteDAO.insert(favorite);
	}
	
	public int remove(String m_id, String[] a_id) throws SQLException {
		return favoriteDAO.remove(m_id, a_id);
	}
	
	public List<Auction> findFavoriteList(int currentPage, int countPerPage, String m_id) throws SQLException {
		return favoriteDAO.findFavoriteList(currentPage, countPerPage, m_id);
	}
	
}
