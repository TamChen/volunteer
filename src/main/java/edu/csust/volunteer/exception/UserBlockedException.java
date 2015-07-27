package edu.csust.volunteer.exception;

public class UserBlockedException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserBlockedException() {
		 super("user.blocked", null);
	}

}
