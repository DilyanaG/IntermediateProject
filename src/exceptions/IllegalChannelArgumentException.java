package exceptions;

public class IllegalChannelArgumentException extends Exception {

	private static final long serialVersionUID = 8611824570169077643L;

	public IllegalChannelArgumentException() {
		super();
	}

	public IllegalChannelArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalChannelArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalChannelArgumentException(String message) {
		super(message);
	}

	public IllegalChannelArgumentException(Throwable cause) {
		super(cause);
	}


}
