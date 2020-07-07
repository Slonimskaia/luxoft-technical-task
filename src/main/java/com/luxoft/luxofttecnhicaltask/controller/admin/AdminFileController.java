package com.luxoft.luxofttecnhicaltask.controller.admin;

import com.luxoft.luxofttecnhicaltask.model.Item;
import com.luxoft.luxofttecnhicaltask.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

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

    @GetMapping("/files/{fileName}")
    public String getFileContent(@PathVariable String fileName, Model model) {

        model.addAttribute("items", storageService.getCsvFile(fileName));
        return "adminFileContent";
    }

    @GetMapping("/files/{fileName}/{primaryKey}")
    public String deleteItemByPrimaryKey(@RequestParam("primary_key") String primaryKey, @PathVariable String fileName, Model model) {
        Item item = storageService.getByPrimaryKey(primaryKey, fileName);
        if (isNull(item)) {
            model.addAttribute("itemNotFound", primaryKey);
        } else {
            storageService.delete(item.getId());
            model.addAttribute("primaryKey", primaryKey);
        }
        model.addAttribute("items", storageService.getCsvFile(fileName));
        model.addAttribute("fileName", fileName);
        return "adminFileContent";
    }
}
