package az.netx.heroes.service;

import az.netx.heroes.model.CustomFile;
import com.google.common.io.Files;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static az.netx.heroes.config.MvcConfig.uploadPath;

@Service
public class FileService {

    public static String saveSingle(CustomFile customFile) throws IOException {
        String resultFilename = "";
        String folder = Objects.nonNull(customFile.getFolder()) ? "/" + customFile.getFolder() : "";
        String currentFilePath = uploadPath + "/" + customFile.getCategory() + folder;

        if (customFile.getFile() != null && !customFile.getFile().getOriginalFilename().isEmpty()) {
            File uploadDir = new File(currentFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            resultFilename = createFile(customFile, currentFilePath);
        }
        return "/file/" + customFile.getCategory() + folder + "/" + resultFilename;
    }

    private static String createFile(CustomFile customFile, String currentFilePath) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String resultFilename = uuid + "." + customFile.getFile().getOriginalFilename();
        customFile.getFile().transferTo(new File(currentFilePath + "/" + resultFilename));
        return resultFilename;
    }

//    public static List<FileEntity> saveMultiple(String category, String folder, List<MultipartFile> files) throws IOException {
//        List<FileEntity> savedFiles = new ArrayList<>();
//        for (MultipartFile file : files) {
//            FileEntity fileEntity = new FileEntity();
//            fileEntity.setFilePath(saveSingle(
//                    CustomFile.builder()
//                            .category(category)
//                            .folder(folder)
//                            .file(file)
//                            .build()
//                    )
//            );
//            fileEntity.setFileType(extensionDetector(file.getOriginalFilename()));
//            fileEntity.setFileCategory(category);
//            savedFiles.add(fileEntity);
//        }
//        return savedFiles;
//    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    private static int extensionDetector(String fileName) {
        switch (Files.getFileExtension(fileName)) {
            case "jpg":
            case "png":
            case "jpeg":
            case "img":
            case "jfif":
                return 1;
            case "mp4":
            case "webm":
            case "flv":
            case "wmv":
                return 2;
            case "mp3":
            case "m4p":
            case "msv":
                return 3;
            case "pdf":
                return 4;
            default:
                return 5;
        }
    }
}
