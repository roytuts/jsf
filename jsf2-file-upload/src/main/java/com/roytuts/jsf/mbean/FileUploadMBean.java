package com.roytuts.jsf.mbean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
		boolean file1Success = false;

		if (file1 != null && file1.getSize() > 0) {
			String fileName = Utils.getFileNameFromPart(file1);

			/**
			 * destination where the file will be uploaded
			 */
			File savedFile = new File("/jsf2-file-upload", fileName);

			// System.out.println("savedFile.toPath(): " + savedFile.toPath());

			try (InputStream input = file1.getInputStream()) {
				Files.copy(input, savedFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}

			file1Success = true;
		}

		boolean file2Success = false;

		if (file2 != null && file2.getSize() > 0) {
			String fileName = Utils.getFileNameFromPart(file2);
			File savedFile = new File("/jsf2-file-upload", fileName);

			System.out.println(savedFile.getAbsolutePath());

			try (InputStream input = file2.getInputStream()) {
				Files.copy(input, savedFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}

			file2Success = true;
		}

		if (file1Success || file2Success) {
			// System.out.println("File uploaded to : " + path);
			/**
			 * set the success message when the file upload is successful
			 */
			setMessage("File successfully uploaded");
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
