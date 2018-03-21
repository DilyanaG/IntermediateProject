package exceptions;

public class IllegalNameException extends Exception {

	public IllegalNameException() {
		super();
	}

	public IllegalNameException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public IllegalNameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalNameException(String arg0) {
		super(arg0);
	}

	public IllegalNameException(Throwable arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 1L;

}
