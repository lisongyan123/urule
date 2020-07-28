package com.bstek.urule.console.repository;

import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.model.VersionFile;
import java.io.InputStream;
import java.util.List;

public interface RepositoryReader {
	String BEAN_ID = "urule.repositoryService";

	List<RepositoryFile> loadProjects(String var1) throws Exception;

	InputStream readFile(String var1) throws Exception;

	List<VersionFile> getVersionFiles(String var1) throws Exception;

	InputStream readFile(String var1, String var2) throws Exception;
}
