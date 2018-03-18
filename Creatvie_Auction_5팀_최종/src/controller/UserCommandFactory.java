package controller;

import controller.action.Action;
import controller.action.DeleteMemberAction;
import controller.action.FavoriteDeleteAction;
import controller.action.FavoriteInsertAction;
import controller.action.FavoriteListAction;
import controller.action.IllegalCommandException;
import controller.action.InsertAction;
import controller.action.ItemDeleteAction;
import controller.action.ItemFileUploadAction;
import controller.action.JoinMemberAction;
import controller.action.ListAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.MainAction;
import controller.action.MatchingInsertAction;
import controller.action.MatchingViewAction;
import controller.action.MyPageAction;
import controller.action.ReplyInsertAction;
import controller.action.UpdateMemberAction;
import controller.action.ViewAction;
import controller.action.SearchAction;
import controller.action.AuctionDeleteAction;

public class UserCommandFactory {

	private UserCommandFactory() {}

	public static UserCommandFactory getInstance() {
		return new UserCommandFactory();
	}

	/*
	 * 수행해야할 명령어에 해당하는 Action객체를 생성.
	 */
	public Action getAction(String command) throws IllegalCommandException {
		Action action = null;

		if (command.equals("list")) {
			action = new ListAction();
		} else if(command.equals("main")) {
			action = new MainAction();
		} else if(command.equals("insert")){
			action = new InsertAction();
		}  else if (command.equals("mem_join")) {
			action = new JoinMemberAction();
		} else if (command.equals("mem_update")) {
			action = new UpdateMemberAction();
		} else if (command.equals("mem_delete")) {
			action = new DeleteMemberAction();
		} else if (command.equals("mem_view")) {
			action = new MyPageAction();
		}else if (command.equals("login")) {
			action = new LoginAction();
		} else if (command.equals("logout")) {
			action = new LogoutAction();
		} else if (command.equals("search")) {
			action = new SearchAction();
		} else if (command.equals("auc_view")){
			action = new ViewAction();
		} else if (command.equals("auc_delete")){
			action = new AuctionDeleteAction();
		} else if (command.equals("itemImageUpload")){
			action = new ItemFileUploadAction();
		}  else if (command.equals("item_delete")) {
			action = new ItemDeleteAction();
		} else if (command.equals("favor_insert")){
			action = new FavoriteInsertAction();
		} else if (command.equals("favor_list")){
			action = new FavoriteListAction();
		} else if (command.equals("favor_delete")){
			action = new FavoriteDeleteAction();
		} else if (command.equals("rep_insert")){
			action = new ReplyInsertAction();
		} else if (command.equals("matching_insert")){
			action = new MatchingInsertAction();
		} else if (command.equals("matching_view")){
			action = new MatchingViewAction();
		} 
		else {
			throw new IllegalCommandException("잘못된 실행명령입니다. 다른 명령을 실행해 주십시요");
		}

		return action;
	}
}


