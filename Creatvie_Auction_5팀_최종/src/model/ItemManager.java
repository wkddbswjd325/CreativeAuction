package model;

import java.sql.SQLException;
import java.util.List;

public class ItemManager {
	private static ItemManager itemMan = new ItemManager();
	private ItemDAO itemDAO;

	private ItemManager() {
		try {
			itemDAO = new ItemDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static ItemManager getInstance() {
		return itemMan;
	}
	
	public int create(Item item) throws SQLException, ExistedItemException {
		if (itemDAO.existedItemId(item.getI_id())) {
			throw new ExistedItemException("파일 업로드 실패.");
		}
		return itemDAO.create(item);
	}

	public int remove(String m_id, String[] i_id) throws SQLException {
		return itemDAO.remove(m_id, i_id);
	}

	public ItemDAO getItemDAO() {
		return this.itemDAO;
	}
	
	public List<Item> findItemList(String m_id) throws SQLException{
	      return itemDAO.findItemList(m_id);
	}
}
