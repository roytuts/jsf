package com.roytuts.jsf.internationalization.i18n;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ViewScoped
@ManagedBean
public class LocaleSwitcher implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of LocaleSwitcher
	 */
	public LocaleSwitcher() {
	}

	// default language - english(en)
	private String language = "en";

	// get the current locale from facescontext
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	// when dropdown value gets changed - so the language and locale
	public void onLanguageChange(ValueChangeEvent vce) {
		if (vce != null) {
			String l = vce.getNewValue().toString();
			setLanguage(l);
			locale = new Locale(l);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} else {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
		}
	}

}
