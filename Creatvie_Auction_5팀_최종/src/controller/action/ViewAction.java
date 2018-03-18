package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import model.Auction;
import model.AuctionManager;
import model.Reply;
import model.ReplyManager;


public class ViewAction implements Action {

	public ActionForward execute(
		HttpServletRequest request,	HttpServletResponse response)
		throws Exception {
			
		int a_id = Integer.parseInt(request.getParameter("a_id"));
		
		String currentPageStr = request.getParameter("currentPage");		
		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		int countPerPage = 1000;

		AuctionManager manager1 = AuctionManager.getInstance();
		Auction auction = manager1.viewAuction(a_id);
		
		ReplyManager manager2 = ReplyManager.getInstance();
		List<Reply> replyList = manager2.findReplyList(currentPage, countPerPage, a_id);
		
		request.setAttribute("auction", auction);		
		request.setAttribute("replyList", replyList);		
		
		ActionForward forward = new ActionForward();
		forward.setPath("auction_view.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}
}

