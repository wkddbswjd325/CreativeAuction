package model;
import java.sql.Date;

public class Reply {
	private int r_id;
	private String r_comment=null;
	private int a_id;
	private int i_id;
	private Date r_date=null;
	private String m_id;
	private String i_img = null;
	
	public int getR_id() {
		return r_id;
	}


	public void setR_id(int r_id) {
		this.r_id = r_id;
	}


	public String getR_comment() {
		return r_comment;
	}


	public void setR_comment(String r_comment) {
		this.r_comment = r_comment;
	}


	public int getA_id() {
		return a_id;
	}


	public void setA_id(int a_id) {
		this.a_id = a_id;
	}


	public int getI_id() {
		return i_id;
	}


	public void setI_id(int i_id) {
		this.i_id = i_id;
	}


	public Date getR_date() {
		return r_date;
	}


	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	public String getI_img(){
		return i_img;
	}
	
	public void setI_img(String i_img){
		this.i_img = i_img;
	}
}
