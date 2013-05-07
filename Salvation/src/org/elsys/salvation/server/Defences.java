package org.elsys.salvation.server;

import java.util.ArrayList;

import org.elsys.salvation.client.Defence;
import org.elsys.salvation.client.FunctionalityManager;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Defences implements IsSerializable
{
	private ArrayList<Defence> netDefences;

	private ArrayList<Defence> hardDefences;

	private ArrayList<Defence> softDefences;

	private FunctionalityManager FM = FunctionalityManager.getInstance();

	public Defences()
	{
		netDefences = FM.getNetDefences();
		hardDefences = FM.getHardDefences();
		softDefences = FM.getSoftDefences();
	}

	public void setNetDefences(ArrayList<Defence> netDefences)
	{
		this.netDefences = netDefences;
	}

	public void setHardDefences(ArrayList<Defence> hardDefences)
	{
		this.hardDefences = hardDefences;
	}

	public void setSoftDefences(ArrayList<Defence> softDefences)
	{
		this.softDefences = softDefences;
	}

	public ArrayList<Defence> getNetDefences()
	{
		return netDefences;
	}

	public ArrayList<Defence> getHardDefences()
	{
		return hardDefences;
	}

	public ArrayList<Defence> getSoftDefences()
	{
		return softDefences;
	}
}