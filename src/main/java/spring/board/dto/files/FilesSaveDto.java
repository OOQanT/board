package spring.board.dto.files;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FilesSaveDto {

    private List<MultipartFile> imageFiles;

}
