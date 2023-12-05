package ua.fam.tos.dto;

import ua.fam.tos.domain.boarditem.assignment.Assignment;
import ua.fam.tos.domain.boarditem.assignment.AssignmentStatus;

import java.util.Date;
import java.util.List;

public class AssignmentDTO {

    private long id;
    private String title;
    private Date publicationTime;
    private String creatorUsername;
    private String approverUsername;
    private String executorUsername;
    private Date deadline;
    private AssignmentStatus status;
    private String text;
    private List<AttachmentDTO> creatorAttachments;
    private List<AttachmentDTO> executorAttachments;

    public AssignmentDTO() {}

    public AssignmentDTO(Assignment assignment) {
        creatorUsername = assignment.getCreator().getUsername();
        executorUsername = assignment.getExecutor().getUsername();
        approverUsername = assignment.getApprover().getUsername();
        text = assignment.getText();
        title = assignment.getTitle();
        status = assignment.getStatus();
        deadline = assignment.getDeadline();
        publicationTime = assignment.getPublicationTime();
        creatorAttachments = assignment.getAttachments()
                .stream()
                .filter(attachment -> attachment.getAttacher().equals(assignment.getCreator()))
                .map(AttachmentDTO::new)
                .toList();
        executorAttachments = assignment.getAttachments()
                .stream()
                .filter(attachment -> attachment.getAttacher().equals(assignment.getExecutor()))
                .map(AttachmentDTO::new)
                .toList();
        id = assignment.getId();
    }

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

    public boolean isReadyForSubmit() {
        return !executorAttachments.isEmpty();
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

    public List<AttachmentDTO> getCreatorAttachments() {
        return creatorAttachments;
    }

    public void setCreatorAttachments(List<AttachmentDTO> creatorAttachments) {
        this.creatorAttachments = creatorAttachments;
    }

    public List<AttachmentDTO> getExecutorAttachments() {
        return executorAttachments;
    }

    public void setExecutorAttachments(List<AttachmentDTO> executorAttachments) {
        this.executorAttachments = executorAttachments;
    }
}
