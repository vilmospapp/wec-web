package org.atasi.evidence.collector.action;

import jodd.io.FileUtil;
import jodd.joy.i18n.I18nInterceptor;
import jodd.madvoc.interceptor.ServletConfigInterceptor;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.InterceptedBy;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.method.POST;
import jodd.madvoc.result.Redirect;
import jodd.util.Base64;
import jodd.util.CommandLine;
import jodd.util.ProcessRunner;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Vilmos Papp <papp.gyorgy.vilmos@gmail.com>
 */

@MadvocAction("collector")
@InterceptedBy({ I18nInterceptor.class, ServletConfigInterceptor.class})
public class CollectorAction {

	@In("urlField")
	String url;

	@In
	boolean testSSL;

	@In
	String emailAddress;

	@POST
	@Action("collect")
	public Object collect() {
		try {
			UUID uuid = UUID.randomUUID();
			String output = "/output/" + uuid.toString();

			CommandLine cmd = CommandLine.cmd("/home/collector/bin/website-evidence-collector")
					.arg("--output").arg(output).arg(url);

			if (testSSL) {
				cmd = cmd.arg("--testssl");
			}

			ProcessRunner.ProcessResult result = cmd.run();

			if (result.getExitCode() == 0) {
				return Redirect.to("/download.report?report="+uuid.toString());
			}
			else {
				if (result.getOutput().indexOf("java.io.IOException") > 0) {
					throw new IOException(result.getOutput().substring(result.getOutput().indexOf("java.io.IOException")));
				}
				else {
					System.out.println(result.getOutput());
				}
			}

		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		catch (RuntimeException re) {
			re.printStackTrace();
		}
		return Redirect.to("/");
	}

	private void _cleanUp(String folder) throws IOException {
		FileUtil.deleteDir(folder);
	}


}
