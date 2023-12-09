package spring.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import spring.board.domain.Board;
import spring.board.domain.Files;
import spring.board.dto.board.BoardDto;
import spring.board.dto.files.FilesResponseDto;
import spring.board.repository.FilesRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
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



    public void deleteFiles(Long contentId){
        List<Files> findFiles = filesRepository.findByBoardId(contentId);

        for (Files findFile : findFiles) {
            try {
                String filePath = fileDir + findFile.getStoreFileName();
                File file = ResourceUtils.getFile(filePath);
                FileSystemUtils.deleteRecursively(file);

                log.info("파일이 성공적으로 삭제되었습니다.");
            }catch (FileNotFoundException ex){
                log.info("파일 삭제 실패: " + ex.getMessage());
            }
        }
    }

    public void deleteFilesAndEntity(Long contentId){
        deleteFiles(contentId); // 실제 저장된 파일을 삭제함

        //저장된 파일 엔티티를 찾아옴
        List<Files> findFiles = filesRepository.findByBoardId(contentId);

        for (Files findFile : findFiles) {
            filesRepository.delete(findFile); // 엔티티 객체 삭제
        }
    }

    public void updateFile(Long contentId, Board editedBoard, BoardDto form) throws IOException {
        deleteFilesAndEntity(contentId);
        save(form,editedBoard);
    }
}
