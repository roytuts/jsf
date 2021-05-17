package com.roytuts.jsf.mbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.roytuts.jsf.util.Utils;

@ManagedBean
@ViewScoped
public class FileUploadMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Part file1;
	private Part file2;
	private String message;

	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part file1) {
		this.file1 = file1;
	}

	public Part getFile2() {
		return file2;
	}

	public void setFile2(Part file2) {
		this.file2 = file2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String uploadFile() throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String path = servletContext.getRealPath("");
		boolean file1Success = false;
		if (file1 != null && file1.getSize() > 0) {
			String fileName = Utils.getFileNameFromPart(file1);
			/**
			 * destination where the file will be uploaded
			 */
			File outputFile = new File(path + File.separator + "WEB-INF" + File.separator + fileName);
			inputStream = file1.getInputStream();
			outputStream = new FileOutputStream(outputFile);
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			file1Success = true;
		}
		boolean file2Success = false;
		if (file2 != null && file2.getSize() > 0) {
			String fileName = Utils.getFileNameFromPart(file2);
			File outputFile = new File(path + File.separator + "WEB-INF" + File.separator + fileName);
			inputStream = file2.getInputStream();
			outputStream = new FileOutputStream(outputFile);
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			file2Success = true;
		}
		if (file1Success || file2Success) {
			System.out.println("File uploaded to : " + path);
			/**
			 * set the success message when the file upload is successful
			 */
			setMessage("File successfully uploaded to " + path);
		} else {
			/**
			 * set the error message when error occurs during the file upload
			 */
			setMessage("Error, select atleast one file!");
		}
		/**
		 * return to the same view
		 */
		return null;
	}
}
