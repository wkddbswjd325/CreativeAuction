package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.ExistedFavoriteException;
import model.Favorite;
import model.FavoriteManager;


public class FavoriteInsertAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
					throws Exception {
		
		HttpSession Session = request.getSession();
		String m_id = (String) Session.getAttribute("m_id");
		
		Favorite favorite = new Favorite();
		favorite.setA_id(Integer.parseInt(request.getParameter("a_id")));
		favorite.setM_id(m_id);
		
		ActionForward forward = new ActionForward();
		try {
			FavoriteManager manager = FavoriteManager.getInstance();
			manager.insert(favorite);
			
			forward.setPath("auction_view.m2?command=auc_view&a_id="+favorite.getA_id());
			forward.setRedirect(true);
					
		} catch (ExistedFavoriteException e) {
			request.setAttribute("exception", e);
			forward.setPath("auction_view.m2?command=auc_view&a_id="+favorite.getA_id());
			forward.setRedirect(false);					
		}
			
		return forward;
	}
}
