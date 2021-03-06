package com.luxoft.luxofttecnhicaltask.controller.client;

import com.luxoft.luxofttecnhicaltask.model.Item;
import com.luxoft.luxofttecnhicaltask.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.util.Objects.isNull;

@Controller
@RequestMapping(value = "/user")
public class UserFileController {

    private final FileStorageService storageService;

    @Autowired
    public UserFileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", storageService.getAllFilesNames());
        return "uploadingFileForm";
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
        return "userFileContent";
    }

    @GetMapping("/files/{fileName}/{primaryKey}")
    public String getItemByPrimaryKey(@RequestParam("primary_key") String primaryKey, @PathVariable String fileName, Model model) {

        Item item = storageService.getByPrimaryKey(primaryKey, fileName);
        if (isNull(item)) {
            model.addAttribute("itemNotFound", primaryKey);
        } else {
            model.addAttribute("item", storageService.getByPrimaryKey(primaryKey, fileName));
        }
        model.addAttribute("items", storageService.getCsvFile(fileName));
        model.addAttribute("fileName", fileName);
        return "userFileContent";
    }

    @ExceptionHandler(SecurityException.class)
    public String handleStorageFileNotFound(SecurityException exception, Model model) {

        model.addAttribute("exception", exception);
        return "exception";
    }
}
