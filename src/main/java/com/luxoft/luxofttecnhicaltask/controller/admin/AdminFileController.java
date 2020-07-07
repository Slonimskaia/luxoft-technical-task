package com.luxoft.luxofttecnhicaltask.controller.admin;

import com.luxoft.luxofttecnhicaltask.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminFileController {

    private final FileStorageService storageService;

    @Autowired
    public AdminFileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", storageService.getAllFilesNames());
        return "listOfFiles";
    }
}
