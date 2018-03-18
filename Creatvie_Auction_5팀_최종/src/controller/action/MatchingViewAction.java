package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import model.Matching;
import model.MatchingManager;
import model.NotMatchedIdException;

public class MatchingViewAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
				
			int a_id = Integer.parseInt(request.getParameter("a_id"));
			
			HttpSession Session = request.getSession();
			String m_id = (String) Session.getAttribute("m_id");
			
			ActionForward forward = new ActionForward();
			try {
				MatchingManager manager = MatchingManager.getInstance();
				Matching matching = manager.viewMatching(a_id, m_id);
				
				request.setAttribute("matching", matching);	
							
				forward.setPath("matching_view.jsp");
				forward.setRedirect(false);
				
			} catch (NotMatchedIdException e) {
				request.setAttribute("exception", e);
				forward.setPath("auction_view.m2?command=auc_view&a_id="+a_id);
				forward.setRedirect(false);					
			}
			
			return forward;
			
		}

}

