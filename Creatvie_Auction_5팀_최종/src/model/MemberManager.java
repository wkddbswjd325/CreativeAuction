package model;

import java.sql.SQLException;
import java.util.List;

public class MemberManager {

	private static MemberManager memMan = new MemberManager();
	private MemberDAO memberDAO;

	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static MemberManager getInstance() {
		return memMan;
	}
	
	public int create(Member member) throws SQLException, ExistedMemberException {
		if (memberDAO.existedMember(member.getM_id())) {
			throw new ExistedMemberException(member.getM_id() + "는 존재하는 아이디입니다.");
		}
		return memberDAO.create(member);
	}

	public int update(Member member) throws SQLException {
		return memberDAO.update(member);
	}	

	public int remove(String m_id) throws SQLException {
		return memberDAO.remove(m_id);
	}

	public Member findMember(String m_id)
		throws SQLException, MemberNotFoundException {
		Member member = memberDAO.findMember(m_id);
		
		if (member == null) {
			throw new MemberNotFoundException(m_id +"는 존재하지 않는 아이디입니다.");
		}		
		return member;
	}

	public List<Member> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return memberDAO.findMemberList(currentPage, countPerPage);
	}

	public boolean login(String m_id, String m_password)
		throws SQLException, MemberNotFoundException, PasswordMismatchException {
		Member member = findMember(m_id);

		if (!member.isMatchPassword(m_password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public MemberDAO getMemberDAO() {
		return this.memberDAO;
	}
}
