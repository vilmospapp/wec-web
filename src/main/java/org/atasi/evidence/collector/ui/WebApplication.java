package org.atasi.evidence.collector.ui;

import jodd.joy.JoyContextListener;

import javax.servlet.annotation.WebListener;

/**
 * @author Vilmos Papp <papp.gyorgy.vilmos@gmail.com>
 */

@WebListener
public class WebApplication  extends JoyContextListener {

	public WebApplication() {
		super();
	}
}
