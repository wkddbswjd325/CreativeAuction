package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.ItemManager;


public class ItemDeleteAction implements Action {

	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
					throws Exception {
		
		HttpSession Session = request.getSession();
		String m_id = (String) Session.getAttribute("m_id");
		
		ItemManager manager = ItemManager.getInstance();
		manager.remove(m_id, request.getParameterValues("chkbox_del"));

		ActionForward forward = new ActionForward();
		forward.setPath("my_page.m2?command=mem_view&m_id="+m_id);
		forward.setRedirect(true);

		return forward;		
	}
}
