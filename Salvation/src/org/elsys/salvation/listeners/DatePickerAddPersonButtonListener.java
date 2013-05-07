package org.elsys.salvation.listeners;

import java.util.ArrayList;
import java.util.Date;

import org.elsys.salvation.client.FunctionalityManager;
import org.elsys.salvation.client.Salvation;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class DatePickerAddPersonButtonListener implements ClickHandler
{
	private Salvation salvation = new Salvation();

	private ArrayList<Date> dates;

	private TextItem textBox;

	private ListBox listBox;

	private FunctionalityManager FM = FunctionalityManager.getInstance();

	public DatePickerAddPersonButtonListener(ArrayList<Date> dates, TextItem textBox, ListBox listBox)
	{
		this.dates = dates;
		this.textBox = textBox;
		this.listBox = listBox;
	}

	@Override
	public void onClick(ClickEvent event)
	{
		if (FM.checkEmpty(textBox))
		{
			SC.say("Enter name!");
		}
		else
		{
			FM.getPerson(dates, textBox.getValueAsString(), listBox.getValue(listBox.getSelectedIndex()));
			RootPanel.get("mainDiv").clear();
			salvation.addPerson();
		}
	}
}
