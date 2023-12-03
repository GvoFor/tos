package ua.fam.tos.domain.boarditem;

import ua.fam.tos.domain.Contributor;

public interface Submittable {
	
	void submit(Contributor submitter);
	void cancel(Contributor canceler);
	
}