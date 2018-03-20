package exceptions;

public class IllegalCommentContentException extends Exception {

	private static final long serialVersionUID = -5224117730328403764L;

	public IllegalCommentContentException() {
		super();
	}

	public IllegalCommentContentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalCommentContentException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalCommentContentException(String message) {
		super(message);
	}

	public IllegalCommentContentException(Throwable cause) {
		super(cause);
	}


}
