package ua.fam.tos.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.fam.tos.domain.boarditem.Attachment;
import ua.fam.tos.domain.exceptions.AttachmentException;
import ua.fam.tos.service.AttachmentService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;

@Controller
@RequestMapping("/boards/{boardId}/items/{itemId}/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/{attachmentId}")
    public void downloadAttachment(HttpServletResponse response,
                                   @PathVariable long boardId,
                                   @PathVariable long itemId,
                                   @PathVariable long attachmentId) {
        try {
            Attachment attachment = attachmentService.getById(boardId, itemId, attachmentId).get();
            Path filePath = Path.of(attachment.getSourcePath());

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            response.setContentType(contentType);
            response.setContentLengthLong(Files.size(filePath));
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                    .filename(attachment.getTitle(), StandardCharsets.UTF_8)
                    .build()
                    .toString());
            Files.copy(filePath, response.getOutputStream());
        } catch (IOException exception) {
            throw new AttachmentException("Couldn't download file: " + exception);
        }
    }

    @PostMapping("/{attachmentId}/delete")
    public String deleteAttachment(@PathVariable long boardId,
                                   @PathVariable long itemId,
                                   @PathVariable long attachmentId,
                                   @RequestParam String redirectUrl) {
        attachmentService.deleteById(boardId, itemId, attachmentId);
        return "redirect:" + redirectUrl;
    }

    @PostMapping("/upload")
    public String uploadAttachment(@PathVariable long boardId,
                                   @PathVariable long itemId,
                                   @RequestParam String redirectUrl,
                                   @RequestParam("file") MultipartFile file,
                                   Principal attacher) {
        if (!file.isEmpty()) {
            attachmentService.upload(boardId, itemId, file, attacher.getName());
        }
        return "redirect:" + redirectUrl;
    }

}