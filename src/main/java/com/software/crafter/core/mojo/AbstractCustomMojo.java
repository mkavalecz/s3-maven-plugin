package com.software.crafter.core.mojo;

import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.software.crafter.core.artifact.Download;
import com.software.crafter.core.artifact.Upload;

/**
 * @author Roman Zimnik
 * @version 1.0.0
 */
public abstract class AbstractCustomMojo extends AbstractMojo {

	@Parameter(property = "accessKey")
	protected String accessKey;

	@Parameter(property = "secretKey")
	protected String secretKey;

	@Parameter(property = "region")
	protected String region;

	@Parameter(property = "downloads")
	protected List<Download> downloads;

	@Parameter(property = "uploads")
	protected List<Upload> uploads;
}
