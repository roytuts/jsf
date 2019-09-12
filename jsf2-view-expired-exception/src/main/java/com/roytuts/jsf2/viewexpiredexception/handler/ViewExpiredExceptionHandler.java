package com.roytuts.jsf2.viewexpiredexception.handler;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ViewExpiredExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler handler;

	public ViewExpiredExceptionHandler(ExceptionHandler handler) {
		this.handler = handler;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return handler;
	}

	// once we override getWrapped(), we need only override those methods we're
	// interested in. In this case, we want to override only handle()
	@Override
	public void handle() throws FacesException {
		
		// iterate over unhandler exceptions using the iterator returned from
		// getUnhandledExceptionQueuedEvents().iterator()
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {

			// The ExeceptionQueuedEvent is a SystemEvent from which we can get the actual
			// ViewExpiredException
			ExceptionQueuedEvent queuedEvent = i.next();
			ExceptionQueuedEventContext queuedEventContext = (ExceptionQueuedEventContext) queuedEvent.getSource();
			Throwable throwable = queuedEventContext.getException();

			if (throwable instanceof ViewExpiredException) {
				ViewExpiredException viewExpiredException = (ViewExpiredException) throwable;
				FacesContext facesContext = FacesContext.getCurrentInstance();

				// for ultimately showing a JSF page we want to extract some information from
				// the exception and place it in request scope,
				// so we can access it via EL in the page
				Map<String, Object> map = facesContext.getExternalContext().getRequestMap();
				NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

				try {
					// we put the current view, where ViewExpiredException occurrs, in
					// "currentViewId" variable
					map.put("currentViewId", viewExpiredException.getViewId());

					// leverage the JSF implicit navigation system and cause the server to navigate
					// to the "viewExpired" page
					// we will show viewExpired page with meaningful message. the view or page name
					// is viewExpired.xhtml
					// viewExpired.xhtml is put on the root path as index.xhtml file
					navigationHandler.handleNavigation(facesContext, null, "viewExpired");

					// render the response
					facesContext.renderResponse();
				} finally {
					// we call remove() on the iterator. This is an important part of the
					// ExceptionHandler usage contract.
					// If you handle an exception, you have to remove it from the list of unhandled
					// exceptions
					i.remove();
				}
			}
		}
		
		getWrapped().handle();
	}

}
