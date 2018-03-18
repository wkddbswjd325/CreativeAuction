package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.MemberManager;

public class DeleteMemberAction implements Action {

	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
			String m_id = request.getParameter("m_id");
			
			MemberManager manager = MemberManager.getInstance();
			manager.remove(m_id);
			
			//세션에 저장된 사용자 이이디 및 세션 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("m_id");
			session.invalidate();

			ActionForward forward = new ActionForward();
			forward.setPath("index.m2");
			forward.setRedirect(true);
				
			return forward;		
		}
	
}
