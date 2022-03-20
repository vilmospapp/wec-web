package org.atasi.evidence.collector.ui;
import jodd.joy.server.Server;

/**
 * @author Vilmos Papp <papp.gyorgy.vilmos@gmail.com>
 */
public class WebApp {
	public static void main(String[] args) {
		Server.create().setPort(8080).start();
	}
}
