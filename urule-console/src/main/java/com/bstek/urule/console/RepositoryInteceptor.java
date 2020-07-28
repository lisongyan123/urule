package com.bstek.urule.console;

public interface RepositoryInteceptor {
	void readFile(String var1);

	void saveFile(String var1, String var2);

	void createFile(String var1, String var2);

	void deleteFile(String var1);

	void renameFile(String var1, String var2);

	void createDir(String var1);

	void createProject(String var1);
}
