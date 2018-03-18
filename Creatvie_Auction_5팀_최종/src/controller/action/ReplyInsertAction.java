package controller.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import model.Item;
import model.ItemManager;
import model.Reply;
import model.ReplyManager;


public class ReplyInsertAction implements Action {
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
				forward.setPath("reply_write.jsp");
				ItemManager itemManager = ItemManager.getInstance();
	            List<Item> itemList = itemManager.findItemList((String)Session.getAttribute("m_id"));
	            request.setAttribute("itemList", itemList);
				forward.setRedirect(false);
				return forward;
		}
		
		
		String m_id = (String) Session.getAttribute("m_id");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd",Locale.KOREA);

		Calendar c = Calendar.getInstance();
		Date r_date = new Date(c.getTimeInMillis());
		dateFormat.format(r_date);

		
		Reply reply = new Reply();
		reply.setA_id(Integer.parseInt(request.getParameter("a_id")));
		reply.setM_id(m_id);
		reply.setR_date(r_date);
		reply.setI_id(Integer.parseInt(request.getParameter("itemId")));
		reply.setR_comment(request.getParameter("r_comment"));
		
		ActionForward forward = new ActionForward();
		try {
			ReplyManager manager = ReplyManager.getInstance();
			manager.create(reply);
			
			request.setAttribute("status", "ok");
			
			forward.setPath("reply_write.jsp?status=ok");
			forward.setRedirect(false);
			
		} catch (Exception e) {
			forward.setPath("reply_write.m2?command=rep_insert&a_id="+request.getParameter("a_id")+"&exception=true");
			forward.setRedirect(true);					
		}			
		return forward;
	}

}
