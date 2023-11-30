package ua.fam.tos.domain.boarditem.assignment;

import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.*;
import ua.fam.tos.domain.exceptions.ApprovalException;
import ua.fam.tos.domain.exceptions.SubmissionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Assignment extends BoardItem implements Approvable, Submittable, Attachable {

    private Contributor approver;
    private Contributor executor;
    private Date deadline;
    private AssignmentStatus status;
	private String text;
	private final List<Attachment> attachments;

    public Assignment() {
        status = AssignmentStatus.UNSUBMITTED;
        text = "";
        attachments = new ArrayList<>();
    }

    public Contributor getApprover() {
        return approver;
    }

    public void setApprover(Contributor approver) {
        this.approver = approver;
    }
	
    public Contributor getExecutor() {
        return executor;
    }

    public void setExecutor(Contributor executor) {
        this.executor = executor;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public Optional<Attachment> getAttachmentById(long id) {
        return attachments.stream()
                .filter(attachment -> attachment.getId() == id)
                .findAny();
    }

    @Override
    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    @Override
    public void removeAttachmentById(long id) {
        attachments.removeIf(attachment -> attachment.getId() == id);
    }
	
	@Override
    public void submit(Contributor submitter) {
        if (executor == null) {
            throw new SubmissionException("Executor wasn't assigned");
        }

		if (!executor.getUsername().equals(submitter.getUsername())) {
			throw new SubmissionException("This isn't your assignment");
		}
		
        switch (getStatus()) {
            case APPROVED -> throw new SubmissionException("An assignment was already approved");
            case SUBMITTED -> throw new SubmissionException("An assignment was already submitted");
        }
        if (attachments.isEmpty()) {
            status = AssignmentStatus.SUBMITTED;
        } else {
            throw new SubmissionException("Nothing to submit");
        }
    }

    @Override
    public void cancel(Contributor canceler) {
        if (executor == null) {
            throw new SubmissionException("Executor wasn't assigned");
        }

		if (!executor.getUsername().equals(canceler.getUsername())) {
			throw new SubmissionException("This isn't your assignment");
		}
		
        switch (getStatus()) {
            case APPROVED -> throw new SubmissionException("An assignment was already approved");
            case SUBMITTED -> status = AssignmentStatus.UNSUBMITTED;
            case UNSUBMITTED -> throw new SubmissionException("An assignment wasn't submitted");
        }
    }

    @Override
    public void approve(Contributor approver) {
        if (this.approver == null) {
            throw new ApprovalException("Approver wasn't assigned");
        }

		if (!this.approver.getUsername().equals(approver.getUsername())) {
			throw new ApprovalException("This isn't your assignment");
		}
		
        switch (getStatus()) {
            case APPROVED -> throw new ApprovalException("An assignment was already approved");
            case SUBMITTED -> status = AssignmentStatus.APPROVED;
            case UNSUBMITTED -> throw new ApprovalException("An assignment wasn't submitted");
        }
    }

    @Override
    public void decline(Contributor decliner) {
        if (approver == null) {
            throw new ApprovalException("Approver wasn't assigned");
        }

		if (!approver.getUsername().equals(decliner.getUsername())) {
			throw new ApprovalException("This isn't your assignment");
		}
		
        switch (getStatus()) {
            case APPROVED -> throw new ApprovalException("An assignment was already approved");
            case SUBMITTED -> status = AssignmentStatus.UNSUBMITTED;
            case UNSUBMITTED -> throw new ApprovalException("An assignment wasn't submitted");
        }
    }
    @Override
    public boolean isKnowAboutContributorWithUsername(String username){
        return (approver.getUsername().equals(username))||
                (executor.getUsername().equals(username))||
                (getCreator().getUsername().equals(username));
    }
}
