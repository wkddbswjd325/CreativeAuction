package model;

import java.sql.SQLException;
import java.util.List;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
			throw new AlreadyUsedItemException("�̹� ���� �������Դϴ�");
		}
		return replyDAO.create(reply);
	}
	
	public Reply findReply(int r_id, int a_id) throws SQLException {
		return findReply(r_id, a_id); 
	}

}
