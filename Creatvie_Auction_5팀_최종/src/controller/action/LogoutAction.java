package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;

public class LogoutAction implements Action{
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
			
			//세션에 저장된 사용자 이이디 및 세션 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("m_id");
			session.invalidate();
			
			//이동할 페이지를 결정.
			ActionForward forward = new ActionForward();
			forward.setPath("index.jsp");
			forward.setRedirect(true);
					
			return forward;
		}
}
