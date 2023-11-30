package ua.fam.tos.domain.boarditem;

import ua.fam.tos.domain.Contributor;

import java.util.Date;

public abstract class BoardItem {
	
	private long id;
	private String title;
	private Date publicationTime;
	private Contributor creator;

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
	
	public Date getPublicationTime() {
		return publicationTime;
	}
	
	public void setPublicationTime(Date publicationTime) {
		this.publicationTime = publicationTime;
	}

	public Contributor getCreator() {
		return creator;
	}

	public void setCreator(Contributor creator) {
		this.creator = creator;
	}

	abstract public boolean isKnowAboutContributorWithUsername(String username);
}