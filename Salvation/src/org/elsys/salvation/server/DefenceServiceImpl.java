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
		saveHardDefences(fm);
		saveNetDefences(fm);
		saveSoftDefences(fm);

		// Get entities.

		// Query q = new Query("DiplomaWork");

		// Query setFilter = q.setFilter(new Query.FilterPredicate("Diplomants", Query.FilterOperator.EQUAL,
		// "diplomants2"));

		// PreparedQuery preparedQuery = datastoreService.prepare(q);
		//
		// Iterable<Entity> results = preparedQuery.asIterable();
		//
		// for (Entity result : results)
		// {
		// System.out.println("Namespace: " + result.getNamespace());
		// System.out.println("Getkey name: " + result.getKey().getName());
		// String property = (String) result.getProperty("Diplomants");
		// System.out.println("DiplomantsName: " + property);
		// }
	}

	@Override
	public void getHardDefences(FunctionalityManager fm)
	{
		// TODO: DELETE
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

	private void saveHardDefences(FunctionalityManager fm)
	{

		ArrayList<Defence> hardDefences = fm.getHardDefences();
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

		// Populate entities.
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
				Entity diplomaEntity = new Entity("Hardware", projectName);
				diplomaEntity.setProperty("Leader", currentDiplomaWork.getLeader().getName());
				diplomaEntity.setProperty("Reviewer", currentDiplomaWork.getReviewer().getName());
				diplomaEntity.setProperty("Diplomants", currentDiplomaWork.getDiplomants());
				diplomaEntity.setProperty("Day", currentHardDefenceDay);

				try
				{
					datastoreService.put(diplomaEntity);
					transaction.commit();
				}
				catch (DatastoreFailureException exc)
				{
					System.out.println("Error in transaction");
				}
				finally
				{
					if (transaction.isActive())
					{
						transaction.rollback();
					}
				}
			}
		}
	}

	private void saveNetDefences(FunctionalityManager fm)
	{
		ArrayList<Defence> netDefences = fm.getNetDefences();
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

		// Populate entities.
		for (Defence currentNetDefence : netDefences)
		{
			Date currentNetDefenceDay = currentNetDefence.getDay();
			ArrayList<DiplomaWork> diplomaWorks = currentNetDefence.getDiplomaWorks();

			for (DiplomaWork currentDiplomaWork : diplomaWorks)
			{
				Transaction transaction = datastoreService.beginTransaction();
				System.out.println("Type: " + currentDiplomaWork.getType());
				System.out.println("Net defence day: " + currentNetDefenceDay);
				String projectName = currentDiplomaWork.getName();
				Entity diplomaEntity = new Entity("Communications", projectName);
				diplomaEntity.setProperty("Leader", currentDiplomaWork.getLeader().getName());
				diplomaEntity.setProperty("Reviewer", currentDiplomaWork.getReviewer().getName());
				diplomaEntity.setProperty("Diplomants", currentDiplomaWork.getDiplomants());
				diplomaEntity.setProperty("Day", currentNetDefenceDay);

				try
				{
					datastoreService.put(diplomaEntity);
					transaction.commit();
				}
				catch (DatastoreFailureException exc)
				{
					System.out.println("Error in transaction");
				}
				finally
				{
					if (transaction.isActive())
					{
						transaction.rollback();
					}
				}
			}
		}
	}

	private void saveSoftDefences(FunctionalityManager fm)
	{
		ArrayList<Defence> softDefences = fm.getSoftDefences();
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

		// Populate entities.
		for (Defence currentSoftDefence : softDefences)
		{
			Date currentSoftDefenceDay = currentSoftDefence.getDay();
			ArrayList<DiplomaWork> diplomaWorks = currentSoftDefence.getDiplomaWorks();

			for (DiplomaWork currentDiplomaWork : diplomaWorks)
			{
				Transaction transaction = datastoreService.beginTransaction();
				System.out.println("Type: " + currentDiplomaWork.getType());
				System.out.println("Soft defence day: " + currentSoftDefenceDay);
				String projectName = currentDiplomaWork.getName();
				Entity diplomaEntity = new Entity("Software", projectName);
				diplomaEntity.setProperty("Leader", currentDiplomaWork.getLeader().getName());
				diplomaEntity.setProperty("Reviewer", currentDiplomaWork.getReviewer().getName());
				diplomaEntity.setProperty("Diplomants", currentDiplomaWork.getDiplomants());
				diplomaEntity.setProperty("Day", currentSoftDefenceDay);
				diplomaEntity.setProperty("DefenceType", currentDiplomaWork.getType());

				try
				{
					datastoreService.put(diplomaEntity);
					transaction.commit();
				}
				catch (DatastoreFailureException exc)
				{
					System.out.println("Error in transaction");
				}
				finally
				{
					if (transaction.isActive())
					{
						transaction.rollback();
					}
				}
			}
		}
	}
}
