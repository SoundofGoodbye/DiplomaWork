package org.elsys.salvation.listeners;

import org.elsys.salvation.client.Salvation;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class NewDataButtonListener implements ClickHandler
{
	private Salvation salvation = new Salvation();

	@Override
	public void onClick(ClickEvent event)
	{
		RootPanel.get("mainDiv").clear();
		// dateRange();
		salvation.addPerson();
	}
}
