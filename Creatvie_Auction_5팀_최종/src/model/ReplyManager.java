package model;

import java.sql.SQLException;
import java.util.List;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class ReplyManager {
	private static ReplyManager replyMan = new ReplyManager();
	private ReplyDAO replyDAO;

	private ReplyManager() {
		try {
			replyDAO = new ReplyDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static ReplyManager getInstance() {
		return replyMan;
	}
	
	public List<Reply> findReplyList(int currentPage, int countPerPage, int a_id)
		throws SQLException {
		return replyDAO.findReplyList(currentPage, countPerPage, a_id);
	}

	public ReplyDAO getReplyDAO() {
		return this.replyDAO;
	}
	
	public int create(Reply reply) throws SQLException, AlreadyUsedItemException {
		if (replyDAO.alreadyUsedItemException(reply.getI_id())) {
			throw new AlreadyUsedItemException("이미 사용된 아이템입니다");
		}
		return replyDAO.create(reply);
	}
	
	public Reply findReply(int r_id, int a_id) throws SQLException {
		return findReply(r_id, a_id); 
	}

}
