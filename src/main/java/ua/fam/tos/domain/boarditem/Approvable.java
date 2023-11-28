package ua.fam.tos.domain.boarditem;

import ua.fam.tos.domain.Contributor;

public interface Approvable {
	
	void approve(Contributor approver);
	void decline(Contributor decliner);
	
}