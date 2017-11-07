package com.sy.ShowSy.utils;
public class TipException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -566156589636870348L;

	public TipException() {
    }

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipException(Throwable cause) {
        super(cause);
    }

}