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
				//�𵨿� �α��� ó���� ����.
				MemberManager manager = MemberManager.getInstance();
				manager.login(m_id, m_password);
		
				//���ǿ� ����� ���̵� ����.
				HttpSession session = request.getSession();
				session.setAttribute("m_id", m_id);
				
				//�̵��� �������� ����.
				forward.setPath("index.jsp");
				forward.setRedirect(false);
				
			} catch (Exception e) {
				/* ExistedUserException�̳� PasswordMismatchException�� �߻� ��
				 * �ٽ� login form (login.jsp)�� ����ڿ��� �����ϰ� ���� �޼����� ���
				 */
				request.setAttribute("exception", e);
				forward.setPath("login.jsp");
				forward.setRedirect(false);					
			}		
			
			return forward;
		}
}
