package spring.board.controller.files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.board.domain.Files;
import spring.board.dto.files.FilesSaveDto;
import spring.board.service.FilesService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FilesController {

    @Value("${file.dir}")
    private String fileDir;

    private final FilesService filesService;

    @GetMapping("/upload")
    public String uploadForm(@ModelAttribute FilesSaveDto filesSaveDto){

        return "files/item-form";
    }

    @PostMapping("/upload")
    public String saveFiles(@ModelAttribute FilesSaveDto filesSaveDto) throws IOException {
        //filesService.save(filesSaveDto);

        return "files/item-form";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileDir + filename);
    }

    @GetMapping("/items")
    public String items(Model model){

        List<Files> files = filesService.findFiles();
        model.addAttribute("items",files);
        return "files/item-view";
    }




}
