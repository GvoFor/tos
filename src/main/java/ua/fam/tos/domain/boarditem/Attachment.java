package ua.fam.tos.domain.boarditem;

import ua.fam.tos.domain.Contributor;

public class Attachment {
	
	private long id;
	private String title;
	private String sourcePath;
	private Contributor attacher;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSourcePath() {
		return sourcePath;
	}
	
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public Contributor getAttacher() {
		return attacher;
	}

	public void setAttacher(Contributor attacher) {
		this.attacher = attacher;
	}
}