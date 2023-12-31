package ua.fam.tos.domain.boarditem.material;

import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.Attachable;
import ua.fam.tos.domain.boarditem.Attachment;
import ua.fam.tos.domain.boarditem.BoardItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Material extends BoardItem implements Attachable {

    private String text;
    private final List<Attachment> attachments;

    private final List<Contributor> viewerList;

    public Material(){
        text = "";
        attachments = new ArrayList<>();
        viewerList = new ArrayList<>();
    }

    public void setText(String text){
        this.text=text;
    }

    public String getText(){
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public void addAttachment(Attachment attachment){
        attachments.add(attachment);
    }

    @Override
    public Optional<Attachment> getAttachmentById(long id) {
        return attachments.stream()
                .filter(attachment -> attachment.getId() == id)
                .findAny();
    }

    @Override
    public void removeAttachmentById(long id){
        attachments.removeIf(attachment -> attachment.getId() == id);
    }

    public List<Contributor> getViewerList() {
        return viewerList;
    }

    public void addViewer(Contributor viewer){
        viewerList.add(viewer);
    }

    @Override
    public boolean isKnowAboutContributorWithUsername(String username) {
        return true;
    }
}