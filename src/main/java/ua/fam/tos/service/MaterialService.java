package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.Attachment;
import ua.fam.tos.domain.boarditem.material.Material;
import ua.fam.tos.dto.MaterialDTO;

import java.util.stream.Collectors;
@Service
public class MaterialService {
    public static MaterialDTO getMaterialDTO(Material material) {
        MaterialDTO dto = new MaterialDTO(material);

        dto.setTitle(material.getTitle());
        dto.setPublicationTime(material.getPublicationTime());
        dto.setCreatorUsername(material.getCreator().getUsername());
        dto.setText(material.getText());
        dto.setAttachmentTitles(material.getAttachments()
                .stream()
                .map(Attachment::getTitle)
                .collect(Collectors.toList()));
        dto.setViewerNameList(material.getViewerList()
                .stream()
                .map(Contributor::getUsername)
                .collect(Collectors.toList()));

        return dto;
    }

}
