package org.elsys.salvation.client;

import org.elsys.salvation.server.Defences;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IDefenceServiceAsync
{
	void saveDefences(FunctionalityManager fm, AsyncCallback<Void> callback);

	void getHardDefences(FunctionalityManager fm, AsyncCallback<FunctionalityManager> callback);

	void getNetDefences(FunctionalityManager fm, AsyncCallback<FunctionalityManager> callback);

	void getSoftDefences(FunctionalityManager fm, AsyncCallback<FunctionalityManager> callback);
}
