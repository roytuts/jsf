package com.roytuts.jsf2.ajax.username.availability.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.roytuts.jsf2.ajax.username.availability.utils.QueryHelper;

@ViewScoped
@ManagedBean
public class UserMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;
	private String userName;
	private QueryHelper queryHelper;

	public UserMBean() {
		this.queryHelper = new QueryHelper();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void isUserNameAvailable() {
		if (queryHelper.isUsernameAvailable(userName)) {
			msg = "<span style=\"color:green;\">Username available</span>";
		} else {
			msg = "<span style=\"color:red;\">Username unavailable</span>";
		}
	}

}
