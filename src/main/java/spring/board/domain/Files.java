package spring.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Files {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeFileName;
    private String uploadFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Files(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void changeUploadFileName(String uploadFileName){
        this.uploadFileName = uploadFileName;
    }

    public void createStoreFileName(){
        int pos = uploadFileName.lastIndexOf(".");
        String ext = uploadFileName.substring(pos + 1);

        String uuId = UUID.randomUUID().toString();
        this.storeFileName = uuId + "." + ext;
    }

    public void setBoard(Board board){
        this.board = board;
        board.getFilesList().add(this);
    }
}
