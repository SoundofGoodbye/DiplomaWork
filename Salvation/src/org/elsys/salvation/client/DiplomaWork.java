package org.elsys.salvation.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DiplomaWork implements IsSerializable
{
	private String projectName;

	private String diplomants;

	private Person leader;

	private Person reviewer;

	private String type;

	public DiplomaWork()
	{
		this.projectName = new String();
		this.diplomants = new String();
		this.leader = new Person();
		this.reviewer = new Person();
		this.type = new String();
	}

	public DiplomaWork(String name, String diplomants, Person leader, Person reviewer, String type)
	{
		this.projectName = name;
		this.diplomants = diplomants;
		this.leader = leader;
		this.reviewer = reviewer;
		this.type = type;
	}

	public String getName()
	{
		return projectName;
	}

	public void setName(String name)
	{
		this.projectName = name;
	}

	public String getDiplomants()
	{
		return diplomants;
	}

	public void setDiplomants(String diplomants)
	{
		this.diplomants = diplomants;
	}

	public Person getLeader()
	{
		return leader;
	}

	public void setLeader(Person leader)
	{
		this.leader = leader;
	}

	public Person getReviewer()
	{
		return reviewer;
	}

	public void setReviewer(Person reviewer)
	{
		this.reviewer = reviewer;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
