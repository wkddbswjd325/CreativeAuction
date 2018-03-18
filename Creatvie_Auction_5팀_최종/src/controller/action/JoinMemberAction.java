package controller.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.ExistedMemberException;
import model.Member;
import model.MemberManager;


public class JoinMemberAction implements Action {

	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {

			//System.out.println(request.getMethod());
			if (request.getMethod().equals("GET")) {	// request an insert form
					ActionForward forward = new ActionForward();
					forward.setPath("mem_join.jsp");
					forward.setRedirect(true);
					return forward;
			}
			
			Member member = new Member();
			member.setM_id(request.getParameter("m_id"));
			member.setM_password(request.getParameter("m_password"));
			member.setM_name(request.getParameter("m_name"));
			member.setM_email(request.getParameter("m_email"));
			member.setM_phone(request.getParameter("m_phone"));
			member.setM_address(request.getParameter("m_address"));
			
			
			ActionForward forward = new ActionForward();
			try {
				MemberManager manager = MemberManager.getInstance();
				manager.create(member);
				
				forward.setPath("index.jsp?m_id=" +member.getM_id());
				forward.setRedirect(true);
						
			} catch (ExistedMemberException e) {
				request.setAttribute("exception", e);
				forward.setPath("mem_join.jsp");
				forward.setRedirect(false);					
			}
				
			return forward;
		}

}
