package model;

public class Item {
	private int i_id;
	private String m_id;
	private String i_img;
	private String i_name;
	
	public Item() {
		i_id = -1;
		m_id = null;
		i_img = null;
		i_name = null;		
	}
	
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getI_img() {
		return i_img;
	}
	public void setI_img(String i_img) {
		this.i_img = i_img;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
}
