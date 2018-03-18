package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.FavoriteManager;

public class FavoriteDeleteAction implements Action {

	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
					throws Exception {
		
		HttpSession Session = request.getSession();
		String m_id = (String) Session.getAttribute("m_id");
		
		FavoriteManager manager = FavoriteManager.getInstance();
		manager.remove(m_id, request.getParameterValues("chkbox_del"));

		ActionForward forward = new ActionForward();
		forward.setPath("favoirite.m2?command=favor_list");
		forward.setRedirect(true);

		return forward;		
	}
}
