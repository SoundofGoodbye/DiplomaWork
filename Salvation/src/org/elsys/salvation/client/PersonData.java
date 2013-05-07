package org.elsys.salvation.client;

import java.util.ArrayList;
import java.util.LinkedList;

public class PersonData
{

	private LinkedList<PersonRecord> personRecords;

	private FunctionalityManager fm;

	private String specialtie;

	private int size;

	private int count;

	public PersonData(FunctionalityManager fm, String specialtie)
	{
		this.fm = fm;
		this.specialtie = specialtie;
	}

	public LinkedList<PersonRecord> getDiplomaRecords()
	{
		if (personRecords == null)
		{
			personRecords = getNewRecords();
		}
		return personRecords;
	}

	public LinkedList<PersonRecord> getNewRecords()
	{
		size = 0;
		count = 0;

		count(fm.getHardDefences());
		count(fm.getNetDefences());
		count(fm.getSoftDefences());

		personRecords = new LinkedList<PersonRecord>();

		if (specialtie.equals("Hardware"))
		{
			getHardDefences();
		}
		else if (specialtie.equals("Software"))
		{
			getSoftDefences();
		}
		else if (specialtie.equals("Communications"))
		{
			getNetDefences();
		}
		else
		{
			getHardDefences();
			getSoftDefences();
			getNetDefences();
		}
		return personRecords;
	}

	public void count(ArrayList<Defence> defences)
	{
		for (int i = 0; i < defences.size(); i++)
		{
			for (int k = 0; k < defences.get(i).getDiplomaWorks().size(); k++)
			{
				size++;
			}
		}
	}

	public void getHardDefences()
	{
		for (int i = 0; i < fm.getHardDefences().size(); i++)
		{
			for (int k = 0; k < fm.getHardDefences().get(i).getJury().size(); k++)
			{
				PersonRecord record = new PersonRecord(	fm.getHardDefences().get(i).getJury().get(k).getName(),
														fm.getHardDefences().get(i).getDay().toString());
//				personRecords[count] = record;
				personRecords.add(record);
				count++;
			}
		}
	}

	public void getSoftDefences()
	{
		for (int i = 0; i < fm.getSoftDefences().size(); i++)
		{
			for (int k = 0; k < fm.getSoftDefences().get(i).getJury().size(); k++)
			{
				PersonRecord record = new PersonRecord(	fm.getSoftDefences().get(i).getJury().get(k).getName(),
														fm.getSoftDefences().get(i).getDay().toString());
//				personRecords[count] = record;
				personRecords.add(record);
				count++;
			}
		}
	}

	public void getNetDefences()
	{
		for (int i = 0; i < fm.getNetDefences().size(); i++)
		{
			for (int k = 0; k < fm.getNetDefences().get(i).getJury().size(); k++)
			{
				PersonRecord record = new PersonRecord(	fm.getNetDefences().get(i).getJury().get(k).getName(),
														fm.getNetDefences().get(i).getDay().toString());
//				personRecords[count] = record;
				personRecords.add(record);
				count++;
			}
		}
	}
}
