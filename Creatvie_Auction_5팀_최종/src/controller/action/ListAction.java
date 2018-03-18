package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.*;


public class ListAction implements Action {


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
		List<Auction> auctionList = manager.findAuctionList(currentPage, countPerPage, request.getParameter("cate"));
		
		//auctionList��ü�� response�� �����Ͽ� ����.
		request.setAttribute("auctionList", auctionList);		
		
		//�̵��� �������� ����.
		ActionForward forward = new ActionForward();
		forward.setPath("auction_list.jsp");
				
		forward.setRedirect(false);
		
		return forward;
	}
}
