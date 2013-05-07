package org.elsys.salvation.listeners;

import org.elsys.salvation.client.FunctionalityManager;
import org.elsys.salvation.client.Salvation;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class DatePickerNextButtonListener implements ClickHandler
{
	private Salvation salvation = new Salvation();

	private FunctionalityManager FM = FunctionalityManager.getInstance();

	@Override
	public void onClick(ClickEvent event)
	{
		if (FM.getReviewers().size() == 0 && FM.getLeaders().size() == 0)
		{
			SC.say("Please enter a reviewer and diploma leader.");
		}
		else if (FM.getReviewers().size() == 0)
		{
			SC.say("Please enter a reviewer.");
		}
		else if (FM.getLeaders().size() == 0)
		{
			SC.say("Please enter diploma leader.");
		}
		else
		{
			// FM.getPerson(dates, textBox.getValueAsString(), listBox.getValue(listBox.getSelectedIndex()));
			RootPanel.get("mainDiv").clear();
			salvation.addDiploma();
		}
	}
}
