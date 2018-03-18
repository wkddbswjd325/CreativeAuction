package controller.action;

import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import model.*;


public class InsertAction implements Action {
	/**
	 * request에 저장되어 있는 인자값으로 User객체를 생성하여 
	 * UserManager의 create메써드를 호출하여 새로운 게시물을
	 * 입력한다. 
	 * 입력을 완료한 후 
	 */
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {

			HttpSession Session = request.getSession();
			if (request.getMethod().equals("GET")) {	// request an insert form
				 ActionForward forward = new ActionForward();
	               ItemManager itemManager = ItemManager.getInstance();
	               List<Item> itemList = itemManager.findItemList((String)Session.getAttribute("m_id"));
	               request.setAttribute("itemList", itemList);
	               //System.out.println(itemList.get(0).getI_name());
	               forward.setPath("auction_write.jsp");
	               forward.setRedirect(false);
	               return forward;
			}
			
			String m_id = (String) Session.getAttribute("m_id");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd",Locale.KOREA);
			
			Calendar c = Calendar.getInstance();
			Date write_date = new Date(c.getTimeInMillis());
			dateFormat.format(write_date);
			Date dead_line = new Date(c.getTimeInMillis());
			dateFormat.format(dead_line);
			
			Auction auction = new Auction();
			auction.setA_title(request.getParameter("auctionTitle"));
			auction.setM_id(m_id);
			auction.setA_state("false");
			auction.setA_content(request.getParameter("auctionContent"));
			auction.setA_writedate(write_date);
			auction.setA_deadline(dead_line);
			auction.setI_id(Integer.parseInt(request.getParameter("itemId")));
			auction.setSmall_cate_id(Integer.parseInt(request.getParameter("auctionSmallCate")));
			auction.setBig_cate_id(Integer.parseInt(request.getParameter("auctionBigCate")));
			
			ActionForward forward = new ActionForward();
			try {
				AuctionManager manager = AuctionManager.getInstance();
				manager.create(auction);

				forward.setPath("auction_view.m2?command=auc_view&a_id="+manager.findAId(auction));
				forward.setRedirect(true);
						
			} catch (Exception e) {
				forward.setPath("auction_write.m2?command=insert&big_cate="+request.getParameter("auctionBigCate")+
						"&small_cate="+request.getParameter("auctionSmallCate")+"&exception=true");
				forward.setRedirect(true);					
			}
				
			return forward;
		}

}
