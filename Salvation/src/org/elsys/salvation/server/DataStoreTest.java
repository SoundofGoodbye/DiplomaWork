package org.elsys.salvation.server;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DataStoreTest
{
	public static void main(String[] args)
	{
		Entity entity = new Entity("Person");
		String kind = entity.getKey().getKind();

		entity.setProperty("Name", "NameOne");

		Key createKey = KeyFactory.createKey(kind, "Person");
		System.out.println("Key: " + createKey);
	}
}
