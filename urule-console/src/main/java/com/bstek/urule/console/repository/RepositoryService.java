package com.bstek.urule.console.repository;

import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.refactor.Item;
import com.bstek.urule.console.repository.refactor.RefactorService;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface RepositoryService extends RepositoryReader {
	String BEAN_ID = "urule.repositoryService";

	boolean fileExistCheck(String var1) throws Exception;

	RepositoryFile createProject(String var1, Principal var2, boolean var3) throws Exception;

	void createDir(String var1, Principal var2) throws Exception;

	void createFile(String var1, String var2, Principal var3) throws Exception;

	void saveFile(String var1, String var2, boolean var3, String var4, Principal var5) throws Exception;

	void deleteFile(String var1, Principal var2) throws Exception;

	void lockPath(String var1, Principal var2) throws Exception;

	void unlockPath(String var1, Principal var2) throws Exception;

	Repository loadRepository(String var1, Principal var2, boolean var3, FileType[] var4, String var5) throws Exception;

	void fileRename(String var1, String var2) throws Exception;

	void exportXml(String var1, OutputStream var2) throws Exception;

	void exportXml(OutputStream var1) throws Exception;

	void importXml(InputStream var1, boolean var2) throws Exception;

	List<RepositoryFile> getDirectories(String var1) throws Exception;

	List<ClientConfig> loadClientConfigs(String var1, boolean var2) throws Exception;

	String getProject(String var1);

	ClientProvider getClientProvider();

	void refactorContent(String var1, Item var2) throws Exception;

	boolean fileExist(String var1) throws Exception;

	List<ProjectVariable> loadProjectLibraries(String var1, Principal var2) throws Exception;

	RefactorService getRefactorService();
}
