package spring.board.service;


import jdk.jfr.StackTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.board.domain.Board;
import spring.board.domain.Files;
import spring.board.dto.board.BoardDto;
import spring.board.dto.files.FilesResponseDto;
import spring.board.repository.FilesRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@StackTrace
@RequiredArgsConstructor
public class FilesService {

    @Value("${file.dir}")
    private String fileDir;

    private final FilesRepository filesRepository;

    public void save(BoardDto boardDto, Board board) throws IOException {
        List<MultipartFile> imageFiles = boardDto.getImageFiles();

        for (MultipartFile imageFile : imageFiles) {
            Files file = new Files(imageFile.getOriginalFilename());
            file.createStoreFileName();
            file.setBoard(board);

            imageFile.transferTo(new File(fileDir + file.getStoreFileName()));
            filesRepository.save(file);
        }
    }


    public List<FilesResponseDto> findFiles(Long contentId) {
        List<FilesResponseDto> files = new ArrayList<>();
        List<Files> findFiles = filesRepository.findByBoardId(contentId);

        for(Files file : findFiles){
            files.add(new FilesResponseDto(file.getStoreFileName()));
        }
        return files;
    }
}
