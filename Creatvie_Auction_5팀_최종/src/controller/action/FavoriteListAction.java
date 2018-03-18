package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import model.*;


public class FavoriteListAction implements Action {
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		String currentPageStr = request.getParameter("currentPage");		

		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;
		
		HttpSession Session = request.getSession();
		String m_id = (String) Session.getAttribute("m_id");

		FavoriteManager manager = FavoriteManager.getInstance();
		List<Auction> favoriteList = manager.findFavoriteList(currentPage, countPerPage, m_id);

		request.setAttribute("FavoriteList", favoriteList);		

		//이동할 페이지를 결정.
		ActionForward forward = new ActionForward();
		forward.setPath("favorite.jsp");

		forward.setRedirect(false);

		return forward;
	}


}

