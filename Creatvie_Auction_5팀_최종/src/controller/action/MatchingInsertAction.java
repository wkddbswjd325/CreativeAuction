package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.AlreadyMatchingException;
import model.AuctionManager;
import model.Matching;
import model.MatchingManager;

public class MatchingInsertAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
	
			HttpSession Session = request.getSession();
			String m_id = (String) Session.getAttribute("m_id");
		
			Matching matching = new Matching();
			
			matching.setA_id(Integer.parseInt(request.getParameter("a_id")));
			matching.setA_mem_id(m_id);
			matching.setR_id(Integer.parseInt(request.getParameter("r_id")));
			
			ActionForward forward = new ActionForward();
			try {
				MatchingManager matManager = MatchingManager.getInstance();
				matManager.insert(matching);
				
				AuctionManager aucManager = AuctionManager.getInstance();
				aucManager.updateState(request.getParameter("a_id"));
				
				
				forward.setPath("index.jsp");
				forward.setRedirect(true);
						
			} catch (AlreadyMatchingException e) {
				request.setAttribute("exception", e);
				forward.setPath("auction_view.m2?command=auc_view&a_id="+request.getParameter("a_id"));
				forward.setRedirect(false);					
			}
				
			return forward;
		}


}
