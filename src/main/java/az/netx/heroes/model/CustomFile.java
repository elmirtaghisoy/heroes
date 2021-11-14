package az.netx.heroes.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Builder
public class CustomFile {
    private String category;
    private String folder;
    private MultipartFile file;
}
