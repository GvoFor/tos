package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/boards/{boardId}/items/{itemId}/attachments")
public class AttachmentController {

    @GetMapping("/{attachmentId}")
    public String downloadAttachment(@PathVariable long boardId,
                                     @PathVariable long itemId,
                                     @PathVariable long attachmentId) {
        System.out.println("Downloading stuff...");
        return "redirect:/boards/" + boardId + "items/" + itemId;
    }

    @PostMapping("/{attachmentId}/delete")
    public String deleteAttachment(@PathVariable long boardId,
                                   @PathVariable long itemId,
                                   @PathVariable long attachmentId) {
        System.out.println("Deleting stuff...");
        return "redirect:/boards/" + boardId + "items/" + itemId;
    }

    @PostMapping("/upload")
    public String uploadAttachment(@PathVariable long boardId,
                                   @PathVariable long itemId,
                                   @RequestParam("file") MultipartFile file) {
        System.out.println("Uploading stuff...");
        return "redirect:/boards/" + boardId + "items/" + itemId;
    }

}
