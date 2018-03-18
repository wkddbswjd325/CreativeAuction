package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.MemberManager;

public class LoginAction implements Action {

	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
			
			// System.out.println(request.getMethod());
			if (request.getMethod().equals("GET")) {	// request a login form
				ActionForward forward = new ActionForward();
				forward.setPath("login.jsp");
				forward.setRedirect(true);
				return forward;		
			}
			
			String m_id = request.getParameter("m_id");
			String m_password = request.getParameter("m_password");
			ActionForward forward = new ActionForward();
			
			try {
				//모델에 로그인 처리를 위임.
				MemberManager manager = MemberManager.getInstance();
				manager.login(m_id, m_password);
		
				//세션에 사용자 이이디 저장.
				HttpSession session = request.getSession();
				session.setAttribute("m_id", m_id);
				
				//이동할 페이지를 결정.
				forward.setPath("index.jsp");
				forward.setRedirect(false);
				
			} catch (Exception e) {
				/* ExistedUserException이나 PasswordMismatchException이 발생 시
				 * 다시 login form (login.jsp)을 사용자에게 전송하고 오류 메세지도 출력
				 */
				request.setAttribute("exception", e);
				forward.setPath("login.jsp");
				forward.setRedirect(false);					
			}		
			
			return forward;
		}
}
