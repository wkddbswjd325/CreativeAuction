package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.ActionForward;
import model.AuctionManager;

public class AuctionDeleteAction implements Action {

	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
					throws Exception {
		String a_id = request.getParameter("a_id");
		
		AuctionManager manager = AuctionManager.getInstance();
		manager.remove(a_id);

		ActionForward forward = new ActionForward();
		forward.setPath("index.jsp");
		forward.setRedirect(true);

		return forward;		
	}
}
