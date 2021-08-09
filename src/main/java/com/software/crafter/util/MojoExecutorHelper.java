package com.software.crafter.util;

import java.util.ArrayList;
import java.util.List;

import org.twdata.maven.mojoexecutor.MojoExecutor;

import com.software.crafter.core.artifact.Download;
import com.software.crafter.core.artifact.Upload;

/**
 * @author Roman Zimnik
 * @version 1.0.0
 */
public class MojoExecutorHelper {

	/**
	 * @param downloads
	 * @return
	 */
	public static MojoExecutor.Element getDownloadsElement(List<Download> downloads) {
		return new MojoExecutor.Element("downloads", getDownloadChildren(downloads));
	}

	/**
	 * @param downloads
	 * @return
	 */
	private static MojoExecutor.Element[] getDownloadChildren(List<Download> downloads) {
		List<MojoExecutor.Element> children = new ArrayList<>();
		for (Download download : downloads) {
			children.add(parseDownload(download));
		}

		return children.toArray(new MojoExecutor.Element[children.size()]);
	}

	/**
	 * @param download
	 * @return
	 */
	private static MojoExecutor.Element parseDownload(Download download) {
		return new MojoExecutor.Element("download",
				new MojoExecutor.Element("fileName", download.getFileName()),
				new MojoExecutor.Element("bucketName", download.getBucketName()),
				new MojoExecutor.Element("path", download.getPath()));
	}

	/**
	 * @param uploads
	 * @return
	 */
	public static MojoExecutor.Element getUploadsElement(List<Upload> uploads) {
		return new MojoExecutor.Element("uploads", getUploadChildren(uploads));
	}

	/**
	 * @param uploads
	 * @return
	 */
	private static MojoExecutor.Element[] getUploadChildren(List<Upload> uploads) {
		List<MojoExecutor.Element> children = new ArrayList<>();
		for (Upload upload : uploads) {
			children.add(parseUpload(upload));
		}

		return children.toArray(new MojoExecutor.Element[children.size()]);
	}

	/**
	 * @param upload
	 * @return
	 */
	private static MojoExecutor.Element parseUpload(Upload upload) {
		return new MojoExecutor.Element("upload",
				new MojoExecutor.Element("fileName", upload.getFileName()),
				new MojoExecutor.Element("bucketName", upload.getBucketName()),
				new MojoExecutor.Element("path", upload.getPath()));
	}

}
