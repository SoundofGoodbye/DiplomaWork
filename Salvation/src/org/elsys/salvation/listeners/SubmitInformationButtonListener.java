package org.elsys.salvation.listeners;

import org.elsys.salvation.client.FunctionalityManager;
import org.elsys.salvation.client.Salvation;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class SubmitInformationButtonListener implements ClickHandler
{
	private Salvation salvation = new Salvation();

	private TextItem projectNameTextBox;

	private TextItem diplomantsNameTextBox;

	private ListBox diplomaLeadersListBox;

	private ListBox reviewersListBox;

	private ListBox specialtiesListBox;

	private ListBox typeListBox;

	private FunctionalityManager FM = FunctionalityManager.getInstance();

	public SubmitInformationButtonListener(TextItem projectNameTextBox,
			TextItem diplomantsNameTextBox,
			ListBox diplomaLeadersListBox,
			ListBox reviewersListBox,
			ListBox specialtiesListBox,
			ListBox typeListBox)
	{
		this.projectNameTextBox = projectNameTextBox;
		this.diplomantsNameTextBox = diplomantsNameTextBox;
		this.diplomaLeadersListBox = diplomaLeadersListBox;
		this.reviewersListBox = reviewersListBox;
		this.specialtiesListBox = specialtiesListBox;
		this.typeListBox = typeListBox;
	}

	@Override
	public void onClick(ClickEvent event)
	{
		if (FM.checkEmpty(projectNameTextBox) || FM.checkEmpty(diplomantsNameTextBox))
		{
			SC.say("Fill all fields!");
		}
		else
		{
			FM.getDiploma(	projectNameTextBox.getValueAsString(),
							diplomantsNameTextBox.getValueAsString(),
							diplomaLeadersListBox.getValue(diplomaLeadersListBox.getSelectedIndex()),
							reviewersListBox.getValue(reviewersListBox.getSelectedIndex()),
							specialtiesListBox.getValue(specialtiesListBox.getSelectedIndex()),
							typeListBox.getValue(typeListBox.getSelectedIndex()));
			FM.generateDefences();
			// defenceSvc.saveDefences(FM, callback);
			RootPanel.get("mainDiv").clear();
			salvation.showDefences();
		}
	}
}
