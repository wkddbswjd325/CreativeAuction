package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import model.Member;
import model.MemberManager;

public class UpdateMemberAction implements Action {

	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
			ActionForward forward = new ActionForward();

		
			if (request.getMethod().equals("GET")) {	// request a login form
				String m_id = request.getParameter("m_id");
		
				MemberManager manager = MemberManager.getInstance();
				Member member = manager.findMember(m_id);
		
				request.setAttribute("member", member);
				forward.setPath("mem_modify.jsp");
			} 
			else {		// "POST" request Ã³¸®			
				Member member = new Member();
				member.setM_id(request.getParameter("m_id"));
				member.setM_password(request.getParameter("m_password"));
				member.setM_name(request.getParameter("m_name"));
				member.setM_email(request.getParameter("m_email"));
				member.setM_phone(request.getParameter("m_phone"));
				member.setM_address(request.getParameter("m_address"));
				//member.setM_score(Integer.parseInt(request.getParameter("m_score")));

				MemberManager manager = MemberManager.getInstance();
				manager.update(member);
					
				forward.setPath("my_page.m2");
				forward.setRedirect(true);
			}
			
			return forward;
		}
	
}
