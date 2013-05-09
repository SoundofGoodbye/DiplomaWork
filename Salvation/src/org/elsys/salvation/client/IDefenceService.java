package org.elsys.salvation.client;

import org.elsys.salvation.server.Defences;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("defences")
public interface IDefenceService extends RemoteService
{
	void saveDefences(FunctionalityManager fm);

	void getHardDefences(FunctionalityManager fm);

	FunctionalityManager getNetDefences(FunctionalityManager fm);

	FunctionalityManager getSoftDefences(FunctionalityManager fm);
}
