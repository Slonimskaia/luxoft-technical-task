package com.luxoft.luxofttecnhicaltask.controller.client;

import com.luxoft.luxofttecnhicaltask.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/user")
public class FileController {

    private final FileStorageService storageService;

    @Autowired
    public FileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", storageService.getAllFilesNames());
        return "userUploadingFileForm";
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/user";
    }

    @GetMapping("/files/{fileName}")
    public String getFileContent(@PathVariable String fileName, Model model) {

        model.addAttribute("items", storageService.getCsvFile(fileName));
        return "fileContent";
    }
}
