package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.Auction;
import model.AuctionManager;
import model.Item;
import model.ItemManager;
import model.Member;
import model.MemberManager;

public class MyPageAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
					throws Exception {
		
		HttpSession Session = request.getSession();
		String m_id = (String) Session.getAttribute("m_id");

		MemberManager manager = MemberManager.getInstance();
		Member member = manager.findMember(m_id);

		request.setAttribute("member", member);
	
		//������ ����
		ItemManager itemManager = ItemManager.getInstance();
		List<Item> itemList = itemManager.findItemList(m_id);
		request.setAttribute("itemList", itemList);
		
		//���Ǹ���Ʈ ����
		String currentPageStr = request.getParameter("currentPage");		

		int currentPage = 1;
		if ( (currentPageStr != null) && (!currentPageStr.equals("")) ) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		int countPerPage = 1000;

		AuctionManager auctionManager = AuctionManager.getInstance();
		List<Auction> myAuctionList = auctionManager.findMyAuctionList(currentPage, countPerPage, m_id);
		
		//auctionList��ü�� response�� �����Ͽ� ����.
		request.setAttribute("myAuctionList", myAuctionList);

		ActionForward forward = new ActionForward();
		forward.setPath("my_page.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
