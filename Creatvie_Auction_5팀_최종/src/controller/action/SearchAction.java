package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.*;

public class SearchAction implements Action {
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String currentPageStr = request.getParameter("currentPage");		

			int currentPage = 1;
			if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
				currentPage = Integer.parseInt(currentPageStr);
			}

			int countPerPage = 1000;

			AuctionManager manager = AuctionManager.getInstance();
			List<Auction> auctionList = null;
			
			if(request.getParameter("select_search").equals("title")) {
				auctionList = manager.searchList_title(currentPage, countPerPage, request.getParameter("search_text"));	
			} else if (request.getParameter("select_search").equals("writer")) {
				auctionList = manager.searchList_writer(currentPage, countPerPage, request.getParameter("search_text"));
			}
			
			//auctionList객체를 response에 저장하여 전달.
			request.setAttribute("auctionList", auctionList);		
			
			//이동할 페이지를 결정.
			ActionForward forward = new ActionForward();
			forward.setPath("auction_list.jsp");
					
			forward.setRedirect(false);
			
			return forward;
		}
}
