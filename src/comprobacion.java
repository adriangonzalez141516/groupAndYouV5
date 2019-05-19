import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author Crunchify.com
 * 
 */

public class comprobacion {

	protected boolean crunchifyEmailValidator(String email) {
		boolean isValid = false;
		try {
			//
			// Create InternetAddress object and validated the supplied
			// address which is this case is an email address.
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			System.out.println("You are in catch block -- Exception Occurred for: " + email);
		}
		return isValid;
	}

	protected void myLogger(String email, boolean valid) {
		System.out.println(email + " is " + (valid ? "a" : "not a") + " valid email address\n");
	}
}