package model;

import java.sql.Date;

public class Auction {
	private int a_id;
	private String a_title = null;
	private String m_id = null;
	private String a_state = null;
	private String a_content = null;
	private Date a_writedate = null;
	private Date a_deadline = null;
	private int i_id;
	private int small_cate_id;
	private int big_cate_id;
	private String small_cate_name = null;
	private String i_img = null;
	
	public String getI_img() {
		return i_img;
	}
	public void setI_img(String i_img) {
		this.i_img = i_img;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_title() {
		return a_title;
	}
	public void setA_title(String a_title) {
		this.a_title = a_title;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getA_state() {
		return a_state;
	}
	public void setA_state(String a_state) {
		this.a_state = a_state;
	}
	public String getA_content() {
		return a_content;
	}
	public void setA_content(String a_content) {
		this.a_content = a_content;
	}
	public Date getA_writedate() {
		return a_writedate;
	}
	public void setA_writedate(Date a_writedate) {
		this.a_writedate = a_writedate;
	}
	public Date getA_deadline() {
		return a_deadline;
	}
	public void setA_deadline(Date a_deadline) {
		this.a_deadline = a_deadline;
	}
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public int getSmall_cate_id() {
		return small_cate_id;
	}
	public void setSmall_cate_id(int small_cate_id) {
		this.small_cate_id = small_cate_id;
	}
	public int getBig_cate_id() {
		return big_cate_id;
	}
	public void setBig_cate_id(int big_cate_id) {
		this.big_cate_id = big_cate_id;
	}
	public String getSmall_cate_name() {
		return small_cate_name;
	}
	public void setSmall_cate_name(String small_cate_name) {
		this.small_cate_name = small_cate_name;
	}
}
