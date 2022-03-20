package org.atasi.evidence.collector.action;

import jodd.io.IOUtil;
import jodd.joy.i18n.I18nInterceptor;
import jodd.madvoc.interceptor.ServletConfigInterceptor;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.InterceptedBy;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;
import jodd.io.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Vilmos Papp <papp.gyorgy.vilmos@gmail.com>
 */

@MadvocAction("download")
@InterceptedBy({ I18nInterceptor.class, ServletConfigInterceptor.class})
public class DownloadAction {
	@In
	String report;

	@In
	HttpServletResponse servletResponse;

	@Action("report")
	public void downloadReport() {

		servletResponse.setContentType("application/pdf");
		servletResponse.setHeader("Content-disposition","inline; filename='report.pdf'");
		File file =  new File("/output/" + report + "/report.pdf");

		servletResponse.setContentLength((int)file.length());

		FileInputStream fileInputStream = null;
		OutputStream responseOutputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			responseOutputStream = servletResponse.getOutputStream();
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			IOUtil.close(fileInputStream);
			IOUtil.close(responseOutputStream);
			try {
				FileUtil.deleteDir(file.getParent());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

