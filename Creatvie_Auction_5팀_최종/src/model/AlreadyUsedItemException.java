package model;

public class AlreadyUsedItemException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyUsedItemException() {
		super();
	}

	public AlreadyUsedItemException(String arg0) {
		super(arg0);
	}

}