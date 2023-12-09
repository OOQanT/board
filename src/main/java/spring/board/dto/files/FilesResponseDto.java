package spring.board.dto.files;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilesResponseDto {

    private String storeFileName;

    public FilesResponseDto(String storeFileName) {
        this.storeFileName = storeFileName;
    }
}
