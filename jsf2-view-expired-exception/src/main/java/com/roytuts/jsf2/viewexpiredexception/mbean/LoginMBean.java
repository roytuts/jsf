package com.roytuts.jsf2.viewexpiredexception.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class LoginMBean {

	// success or error message to be shown on the view
	private String message;
	// username
	private String username;
	// password
	private String password;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// login action
	public String doLogin() {
		// check username is empty
		if (username == null || username.trim().length() <= 0) {
			setMessage("<span style=\"color:red;\">Username is required field</span>");
			return null;
		}
		// check password is empty
		if (password == null || password.length() <= 0) {
			setMessage("<span style=\"color:red;\">Password is required field</span>");
			return null;
		}
		// authenticate with username and password
		if (!"soumitra".equalsIgnoreCase(username.trim()) && !"soumitra".equalsIgnoreCase(password)) {
			// set error message if different credentials found. set color:red for error
			// message
			setMessage("<span style=\"color:red;\">Try with username: soumitra and password: soumitra</span>");
			return null;
		}
		// get non-transacted session from facescontext
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		// set maximum interval time period for client interacting with session. can be
		// set in web.xml also
		session.setMaxInactiveInterval(5);// in seconds
		// put the username in session variable
		session.setAttribute("username", username);
		// redirect to home page after login. faces-redirect=true for completely
		// redirecting to the new url
		return "home.jsf?faces-redirect=true";
	}

	// logout action
	public String doLogout() {
		// invalidate the session along with all session variables for the facescontext
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		// redirect to login page
		return "index.jsf?faces-redirect=true";
	}

}
