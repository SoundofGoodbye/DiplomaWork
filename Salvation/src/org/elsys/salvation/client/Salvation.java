package org.elsys.salvation.client;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.elsys.salvation.listeners.AddProjectAndDiplomantsOneMoreButtonListener;
import org.elsys.salvation.listeners.DatePickerAddPersonButtonListener;
import org.elsys.salvation.listeners.DatePickerNextButtonListener;
import org.elsys.salvation.listeners.DiplomaWorkTypes;
import org.elsys.salvation.listeners.GridTableAddMoreButtonListener;
import org.elsys.salvation.listeners.GridTableBackButtonListener;
import org.elsys.salvation.listeners.NewDataButtonListener;
import org.elsys.salvation.listeners.SubmitInformationButtonListener;
import org.elsys.salvation.server.Defences;
import org.elsys.salvation.server.ObjectSerializationToMysql;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class Salvation implements EntryPoint
{
	private static final String ADD_PROJECT_AND_DIPLOMA_BACK_BUTTON_LABEL = "Back";

	private static final String ADD_PROJECT_AND_DIPLOMA_ONE_MORE_BUTTON_LABEL = "One More";

	private static final String SUBMIT_INFORMATION_BUTTON_LABEL = "Submit";

	private static final String TYPES_LISTBOX_OTHER_LABEL = "Other";

	private static final String TYPES_LISTBOX_WEB_SITE_APP_LABEL = "Web Site/App";

	private static final String TYPES_LISTBOX_PLUG_IN_DRIVER_LABEL = "Plug-in/Driver";

	private static final String TYPES_LISTBOX_GAME_MEDIA_LABEL = "Game/Media";

	private static final String TYPES_LISTBOX_SOFTWARE_TYPE_TITLE_LABEL = "Software Type";

	private static final String SPECIALTIES_LISTBOX_COMMUNICATIONS_LABEL = "Communications";

	private static final String SPECIALTIES_LISTBOX_SOFTWARE_LABEL = "Software";

	private static final String SPECIALTIES_LISTBOX_HARDWARE_LABEL = "Hardware";

	private static final String DIPLOMANTS_NAME_TEXTBOX_LABEL = "Diplomants name/s";

	private static final String PROJECT_NAME_TEXTBOX_LABEL = "Project name";

	private static final String DATEPICKER_PAGE_NEXT_BUTTON_LABEL = "Next";

	private static final String ADD_PERSON_BUTTON_LABEL = "Add person";

	private static final String REVIEWER = "Reviewer";

	private static final String DIPLOMA_LEADER = "Diploma Leader";

	private static final String REVIEWER_DIPLOMALEADER_TEXTBOX_NAME_LABEL = "Name";

	private static final String NEW_DATA_BUTTON_LABEL = "New";

	private FunctionalityManager FM = FunctionalityManager.getInstance();

	private IDefenceServiceAsync defenceSvc = (IDefenceServiceAsync) GWT.create(IDefenceService.class);

	private ServiceDefTarget target = (ServiceDefTarget) defenceSvc;

	public void onModuleLoad()
	{
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "Salvation");
		Button newData = new Button(NEW_DATA_BUTTON_LABEL);
		newData.addStyleName("newDataButton");
		newData.setWidth(100);
		// final Button existingData = new Button("Existing");
		HorizontalPanel mainHorizontalPanel = new HorizontalPanel();

		Label lastUpdatedLabel = new Label();

		mainHorizontalPanel.add(newData);
		mainHorizontalPanel.setCellHorizontalAlignment(newData, HasHorizontalAlignment.ALIGN_CENTER);

		// mainHorizontalPanel.add(existingData);
		mainHorizontalPanel.add(lastUpdatedLabel);

		RootPanel.get("mainDiv").add(mainHorizontalPanel);
		newData.addClickHandler(new NewDataButtonListener());

		// existingData.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// RootPanel.get("mainDiv").clear();
		// editDiploma();
		// }
		// });
	}

	protected void dateRange()
	{
		VLayout layout = new VLayout(3);

		DateRangeItem dateRangeItem = new DateRangeItem("dri", "Date Range");
		dateRangeItem.setAllowRelativeDates(true);

		final DateRange dateRange = new DateRange();
		// dateRange.setRelativeStartDate(RelativeDate.YESTERDAY);
		// dateRange.setRelativeEndDate(RelativeDate.YESTERDAY);
		dateRangeItem.setValue(dateRange);

		final Button nextButton = new Button(DATEPICKER_PAGE_NEXT_BUTTON_LABEL);
		nextButton.setWidth(100);
		nextButton.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				// FM.setStartDate(new Date(dateRange.getStartDate().getDate(),
				// dateRange.getStartDate().getMonth(),dateRange.getStartDate().getYear()));
				// FM.setEndDate(new Date(dateRange.getEndDate().getDate(),
				// dateRange.getEndDate().getMonth(),dateRange.getEndDate().getYear()));
				RootPanel.get("mainDiv").clear();
				addPerson();
			}
		});

		Button backButton = new Button(ADD_PROJECT_AND_DIPLOMA_BACK_BUTTON_LABEL);
		backButton.setWidth(100);
		backButton.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				RootPanel.get("mainDiv").clear();
				onModuleLoad();
			}
		});

		DynamicForm form = new DynamicForm();
		form.setItems(dateRangeItem);
		layout.addMember(form);
		layout.addMember(nextButton);
		layout.addMember(backButton);

		layout.draw();
		RootPanel.get("mainDiv").add(layout);

	}

	public void addPerson()
	{
		// SC.say("Pick dates in this range:" + FM.getStartDate().getDate() + "/" + (FM.getStartDate().getMonth() + 1)
		// + "/20" + (FM.getStartDate().getYear() - 100) + " : " + FM.getEndDate().getDate() + "/"
		// + (FM.getEndDate().getMonth() + 1) + "/20" + (FM.getEndDate().getYear() - 100));
		final ArrayList<Date> dates = new ArrayList<Date>();

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		HorizontalPanel buttonsPanel = new HorizontalPanel();

		DynamicForm textBoxForm = new DynamicForm();

		final TextItem textBox = new TextItem();
		textBox.setWidth(110);
		textBox.setTitle(REVIEWER_DIPLOMALEADER_TEXTBOX_NAME_LABEL);

		textBoxForm.setFields(textBox);
		textBoxForm.setWidth(150);

		final ListBox listBox = new ListBox();
		listBox.setPixelSize(150, 25);
		listBox.addItem(DIPLOMA_LEADER);
		listBox.addItem(REVIEWER);
		listBox.addItem("Both");

		final DatePicker datePicker = new DatePicker();
		datePicker.setPixelSize(160, 160);
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>()
		{
			@SuppressWarnings("deprecation")
			public void onValueChange(ValueChangeEvent<Date> event)
			{
				Date currentDate = new Date();
				int currentDateDay = currentDate.getDate();
				int currentDateMonth = currentDate.getMonth();
				int currentDateYear = currentDate.getYear();
				Date eventValue = event.getValue();
				if (eventValue.getYear() < currentDateYear)
				{
					SC.say("The current date is in the past. Please choose a different date.");
				}
				else if (eventValue.getDate() < currentDateDay || eventValue.getMonth() < currentDateMonth)
				{
					SC.say("The current date is in the past. Please choose a different date.");
				}
				else if (dates.add(event.getValue()))
				{
					datePicker.setTransientEnabledOnDates(false, event.getValue());

					Date date = event.getValue();
					String dateString = DateTimeFormat.getFormat("MM/dd/yyyy").format(date);

					SC.say(dateString + " Added to available dates.");
				}
			}
		});

		Button oneMoreButton = new Button(ADD_PERSON_BUTTON_LABEL);
		oneMoreButton.setWidth(230);
		oneMoreButton.addClickHandler(new DatePickerAddPersonButtonListener(dates, textBox, listBox));

		Button next = new Button(DATEPICKER_PAGE_NEXT_BUTTON_LABEL);
		next.setWidth(230);
		next.addClickHandler(new DatePickerNextButtonListener());

		// Button back = new Button("Back");
		// back.setWidth(100);
		// back.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// RootPanel.get("mainDiv").clear();
		// FM.clearAllData();
		// onModuleLoad();
		// }
		// });

		horizontalPanel.add(textBoxForm);
		horizontalPanel.add(datePicker);
		horizontalPanel.add(listBox);

		// buttonsPanel.add(back);
		buttonsPanel.add(oneMoreButton);
		buttonsPanel.add(next);

		RootPanel.get("mainDiv").add(horizontalPanel);
		RootPanel.get("mainDiv").add(buttonsPanel);
	}

	public void addDiploma()
	{
		HorizontalPanel buttonHorizontalPanel = new HorizontalPanel();
		HorizontalPanel listsHorizontalPanel = new HorizontalPanel();
		HorizontalPanel typeListsHorizontalPanel = new HorizontalPanel();
		VerticalPanel mainVerticalPanel = new VerticalPanel();

		final TextItem projectNameTextBox = new TextItem();
		projectNameTextBox.setTitle(PROJECT_NAME_TEXTBOX_LABEL);
		projectNameTextBox.setWidth(415);
		final TextItem diplomantsNameTextBox = new TextItem();
		diplomantsNameTextBox.setTitle(DIPLOMANTS_NAME_TEXTBOX_LABEL);
		diplomantsNameTextBox.setWidth(395);

		DynamicForm projectNameForm = new DynamicForm();
		projectNameForm.setFields(projectNameTextBox);
		projectNameForm.setWidth(450);
		DynamicForm diplomantsNameForm = new DynamicForm();
		diplomantsNameForm.setFields(diplomantsNameTextBox);
		diplomantsNameForm.setWidth(450);

		final ListBox diplomaLeadersListBox = new ListBox();
		diplomaLeadersListBox.setPixelSize(225, 25);
		final ListBox reviewersListBox = new ListBox();
		reviewersListBox.setPixelSize(225, 25);

		Iterator<Person> i = FM.getLeaders().iterator();
		while (i.hasNext())
		{
			diplomaLeadersListBox.addItem(i.next().getName());

		}

		Iterator<Person> k = FM.getReviewers().iterator();
		while (k.hasNext())
		{
			reviewersListBox.addItem(k.next().getName());
		}

		final ListBox specialtiesListBox = new ListBox();
		specialtiesListBox.setTitle("Specialties");
		specialtiesListBox.addItem(DiplomaWorkTypes.HARDWARE.toString());
		specialtiesListBox.addItem(DiplomaWorkTypes.SOFTWARE.toString());
		specialtiesListBox.addItem(DiplomaWorkTypes.COMMUNICATION.toString());
		specialtiesListBox.setPixelSize(225, 25);

		final ListBox typeListBox = new ListBox();
		typeListBox.setTitle(TYPES_LISTBOX_SOFTWARE_TYPE_TITLE_LABEL);
		typeListBox.addItem(TYPES_LISTBOX_GAME_MEDIA_LABEL);
		typeListBox.addItem(TYPES_LISTBOX_PLUG_IN_DRIVER_LABEL);
		typeListBox.addItem(TYPES_LISTBOX_WEB_SITE_APP_LABEL);
		typeListBox.addItem(TYPES_LISTBOX_OTHER_LABEL);
		typeListBox.setEnabled(false);
		typeListBox.setPixelSize(225, 25);

		specialtiesListBox.addChangeHandler(new ChangeHandler()
		{
			@Override
			public void onChange(ChangeEvent event)
			{
				if (specialtiesListBox.getValue(specialtiesListBox.getSelectedIndex())
										.equals(SPECIALTIES_LISTBOX_SOFTWARE_LABEL))
				{
					typeListBox.setEnabled(true);
				}
				else
				{
					typeListBox.setEnabled(false);
				}
			}
		});

		Button submitButton = new Button(SUBMIT_INFORMATION_BUTTON_LABEL);
		submitButton.setWidth(150);
		submitButton.addClickHandler(new SubmitInformationButtonListener(	projectNameTextBox,
																			diplomantsNameTextBox,
																			diplomaLeadersListBox,
																			reviewersListBox,
																			specialtiesListBox,
																			typeListBox));

		Button oneMoreButton = new Button(ADD_PROJECT_AND_DIPLOMA_ONE_MORE_BUTTON_LABEL);
		oneMoreButton.setWidth(150);
		oneMoreButton.addClickHandler(new AddProjectAndDiplomantsOneMoreButtonListener(	projectNameTextBox,
																						diplomantsNameTextBox,
																						diplomaLeadersListBox,
																						reviewersListBox,
																						specialtiesListBox,
																						typeListBox));

		Button back = new Button(ADD_PROJECT_AND_DIPLOMA_BACK_BUTTON_LABEL);
		back.setWidth(150);
		back.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				RootPanel.get("mainDiv").clear();
				addPerson();
			}
		});

		mainVerticalPanel.add(projectNameForm);
		mainVerticalPanel.add(diplomantsNameForm);

		buttonHorizontalPanel.add(back);
		buttonHorizontalPanel.add(oneMoreButton);
		buttonHorizontalPanel.add(submitButton);

		listsHorizontalPanel.add(diplomaLeadersListBox);
		listsHorizontalPanel.add(reviewersListBox);

		typeListsHorizontalPanel.add(specialtiesListBox);
		typeListsHorizontalPanel.add(typeListBox);

		mainVerticalPanel.add(listsHorizontalPanel);
		mainVerticalPanel.add(typeListsHorizontalPanel);
		mainVerticalPanel.add(buttonHorizontalPanel);

		RootPanel.get("mainDiv").add(mainVerticalPanel);
	}

	public void showDefences()
	{
		Button showSoftwareDefences = new Button("Show Software");
		showSoftwareDefences.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				RootPanel.get("mainDiv").clear();
				showDefence(DiplomaWorkTypes.SOFTWARE.toString());
			}
		});

		Button showHardwareDefences = new Button("Show Hardware");
		showHardwareDefences.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				RootPanel.get("mainDiv").clear();
				showDefence(DiplomaWorkTypes.HARDWARE.toString());
			}
		});

		Button showCommunicationsDefences = new Button("Show Communications");
		showCommunicationsDefences.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				RootPanel.get("mainDiv").clear();
				showDefence(DiplomaWorkTypes.COMMUNICATION.toString());
			}
		});

		Button showAllDefences = new Button("Show All");
		showAllDefences.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				RootPanel.get("mainDiv").clear();
				showDefence("All");
			}
		});

		Button back = new Button(ADD_PROJECT_AND_DIPLOMA_BACK_BUTTON_LABEL);
		back.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				RootPanel.get("mainDiv").clear();
				addDiploma();
			}
		});

		RootPanel.get("mainDiv").add(showSoftwareDefences);
		RootPanel.get("mainDiv").add(showCommunicationsDefences);
		RootPanel.get("mainDiv").add(showHardwareDefences);
		RootPanel.get("mainDiv").add(showAllDefences);
		RootPanel.get("mainDiv").add(back);
	}

	public void showDefence(String specialtie)
	{
		HorizontalPanel buttonsPanel = new HorizontalPanel();

		Button addMoreButton = new Button("Add More");
		addMoreButton.setWidth(325);
		addMoreButton.addClickHandler(new GridTableAddMoreButtonListener());

		Button back = new Button(ADD_PROJECT_AND_DIPLOMA_BACK_BUTTON_LABEL);
		back.setWidth(325);
		back.addClickHandler(new GridTableBackButtonListener());

		Button saveToDataBase = new Button("Save Info");
		saveToDataBase.setWidth(325);
		saveToDataBase.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				System.out.println("clicked");

				AsyncCallback callback = new AsyncCallback()
				{
					public void onSuccess(Object result)
					{
						Window.alert("SUCCESS");
					}

					public void onFailure(Throwable caught)
					{
						System.out.println(caught.getCause().toString());
						Window.alert("FAIL");
					}
				};
				defenceSvc.saveDefences(FM, callback);
			}
		});

		buttonsPanel.add(back);
		buttonsPanel.add(addMoreButton);
		buttonsPanel.add(saveToDataBase);

		RootPanel.get("mainDiv").add(diplomaListGrid(specialtie));
		RootPanel.get("mainDiv").add(personListGrid(specialtie));
		RootPanel.get("mainDiv").add(buttonsPanel);
	}

	private ListGrid personListGrid(String specialtie)
	{
		ListGrid personGrid = new ListGrid();
		personGrid.setWidth(650);
		personGrid.setHeight(224);
		personGrid.setShowAllRecords(true);
		// diplomaGrid.setCanEdit(true);
		// diplomaGrid.setEditEvent(ListGridEditEvent.CLICK);
		// diplomaGrid.setModalEditing(true);

		PersonData personData = new PersonData(FM, specialtie);

		ListGridField nameField = new ListGridField("name", "Project Name");
		ListGridField dateField = new ListGridField("date", "Date");
		personGrid.setFields(new ListGridField[] {nameField, dateField});

		LinkedList<PersonRecord> diplomaRecords = personData.getDiplomaRecords();

		PersonRecord[] personRecordToArray = new PersonRecord[diplomaRecords.size()];

		for (int i = 0; i < diplomaRecords.size(); i++)
		{
			personRecordToArray[i] = diplomaRecords.get(i);
		}

		personGrid.setData(personRecordToArray);

		return personGrid;
	}

	private ListGrid diplomaListGrid(String specialtie)
	{
		ListGrid diplomaGrid = new ListGrid();
		diplomaGrid.setPosition(Positioning.RELATIVE);
		diplomaGrid.setWidth(650);
		diplomaGrid.setHeight(224);
		diplomaGrid.setShowAllRecords(true);
		// diplomaGrid.setCanEdit(true);
		// diplomaGrid.setEditEvent(ListGridEditEvent.CLICK);
		// diplomaGrid.setModalEditing(true);

		DiplomaData dd = new DiplomaData(FM, specialtie);

		ListGridField nameField = new ListGridField("name", "Project Name");
		ListGridField diplomantsField = new ListGridField("diplomants", "Diplomants");
		ListGridField leaderField = new ListGridField("leader", "Leader");
		ListGridField reviewerField = new ListGridField("reviewer", REVIEWER);
		ListGridField typeField = new ListGridField("type", "Type");
		typeField.setWidth(130);
		ListGridField dateField = new ListGridField("date", "Date");
		diplomaGrid.setFields(new ListGridField[] {nameField, diplomantsField, leaderField, reviewerField, typeField,
				dateField});
		diplomaGrid.setData(dd.getDiplomaRecords());

		return diplomaGrid;
	}

	public FunctionalityManager getSalvationsFM()
	{
		return FM;
	}
}