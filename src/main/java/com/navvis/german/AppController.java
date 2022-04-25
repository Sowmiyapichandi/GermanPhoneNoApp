package com.navvis.german;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("/api")
public class AppController {

    private final PhoneNumbersService storageService;

    @Autowired
    public AppController(PhoneNumbersService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public List<Long> listAllTasks() throws IOException {
        List<Long> taskIDs = storageService.loadAll();
        return taskIDs;
    }

    //@PostMapping("/")
    @RequestMapping(
            path = "/",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        Long taskID = storageService.store(file);
        return taskID;
    }

    @GetMapping("/task/{id}")
    public HashSet<String> load(@PathVariable Long id) throws IOException {
        HashSet<String> phoneNumbers = storageService.load(id);
        return phoneNumbers;
    }

    @DeleteMapping("/task/{id}")
    public String listAllTasks(@PathVariable Long id) throws IOException {
        storageService.deletePhoneNumbers(id);
        return "All phoneNumbers are deleted for task id " + id;
    }

}