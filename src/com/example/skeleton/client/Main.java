package com.example.skeleton.client;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.user.client.ui.RootPanel;


public class Main implements EntryPoint{


	public void onModuleLoad(){
		RootPanel rootPanel = RootPanel.get();

		rootPanel.add(new DatePanel());
	}

	private static final Logger logger = Logger.getLogger(Main.class.getName());

}
