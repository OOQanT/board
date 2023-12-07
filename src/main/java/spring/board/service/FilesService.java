package spring.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.board.domain.Files;
import spring.board.dto.files.FilesSaveDto;
import spring.board.repository.FilesRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FilesService {
    @Value("${file.dir}")
    private String fileDir;

    private final FilesRepository filesRepository;
    public void save(FilesSaveDto filesSaveDto) throws IOException {
        List<MultipartFile> imageFiles = filesSaveDto.getImageFiles();

        for (MultipartFile imageFile : imageFiles) {
            Files files = new Files(imageFile.getOriginalFilename());
            files.createStoreFileName();

            imageFile.transferTo(new File(fileDir + files.getStoreFileName()));
            filesRepository.save(files);
        }
    }

    public List<Files> findFiles(){
        return filesRepository.findAll();
    }
}
