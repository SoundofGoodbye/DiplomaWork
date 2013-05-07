package org.elsys.salvation.server;

import java.util.ArrayList;
import java.util.Date;

import org.elsys.salvation.client.Defence;
import org.elsys.salvation.client.DiplomaWork;
import org.elsys.salvation.client.FunctionalityManager;
import org.elsys.salvation.client.IDefenceService;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DefenceServiceImpl extends RemoteServiceServlet implements IDefenceService
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void saveDefences(FunctionalityManager fm)
	{
		ArrayList<Defence> hardDefences = fm.getHardDefences();
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

		for (Defence currentHardDefence : hardDefences)
		{
			Date currentHardDefenceDay = currentHardDefence.getDay();
			ArrayList<DiplomaWork> diplomaWorks = currentHardDefence.getDiplomaWorks();

			for (DiplomaWork currentDiplomaWork : diplomaWorks)
			{
				Transaction transaction = datastoreService.beginTransaction();
				System.out.println("Type: " + currentDiplomaWork.getType());
				System.out.println("Hard defence day: " + currentHardDefenceDay);
				String projectName = currentDiplomaWork.getName();
				Entity diplomaEntity = new Entity("DiplomaWork", projectName);
				diplomaEntity.setProperty("Leader", currentDiplomaWork.getLeader().getName());
				diplomaEntity.setProperty("Reviewer", currentDiplomaWork.getReviewer().getName());
				diplomaEntity.setProperty("Diplomants", currentDiplomaWork.getDiplomants());

				try
				{
					datastoreService.put(diplomaEntity);
				}
				catch (DatastoreFailureException exc)
				{
					System.out.println("Error in transaction");
				}
				finally
				{
					transaction.commit();
				}
			}
		}
	}

	@Override
	public FunctionalityManager getHardDefences(FunctionalityManager fm)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FunctionalityManager getNetDefences(FunctionalityManager fm)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FunctionalityManager getSoftDefences(FunctionalityManager fm)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
