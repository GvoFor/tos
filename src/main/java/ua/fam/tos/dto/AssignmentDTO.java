package ua.fam.tos.dto;

import ua.fam.tos.domain.boarditem.assignment.Assignment;
import ua.fam.tos.domain.boarditem.assignment.AssignmentStatus;

import java.util.Date;
import java.util.List;

public class AssignmentDTO {

    private String title;
    private Date publicationTime;
    private String creatorUsername;
    private String approverUsername;
    private String executorUsername;
    private Date deadline;
    private AssignmentStatus status;
    private String text;
    private List<AttachmentDTO> attachments;

    public AssignmentDTO(Assignment assignment) {
        creatorUsername = assignment.getCreator().getUsername();
        executorUsername = assignment.getExecutor().getUsername();
        approverUsername = assignment.getApprover().getUsername();
        text = assignment.getText();
        title = assignment.getTitle();
        status = assignment.getStatus();
        deadline = assignment.getDeadline();
        publicationTime = assignment.getPublicationTime();
        attachments = assignment.getAttachments()
                .stream()
                .map(AttachmentDTO::new)
                .toList();
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

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getApproverUsername() {
        return approverUsername;
    }

    public void setApproverUsername(String approverUsername) {
        this.approverUsername = approverUsername;
    }

    public String getExecutorUsername() {
        return executorUsername;
    }

    public void setExecutorUsername(String executorUsername) {
        this.executorUsername = executorUsername;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isApproved() {
        return status == AssignmentStatus.APPROVED;
    }

    public boolean isSubmitted() {
        return status == AssignmentStatus.SUBMITTED;
    }

    public boolean isUnsubmitted() {
        return status == AssignmentStatus.UNSUBMITTED;
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

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }
}
