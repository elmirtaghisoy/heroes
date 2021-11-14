package az.netx.heroes.util;

import java.util.UUID;

public class FileUtil {
    public static String generateRandomFolderName() {
        return UUID.randomUUID().toString();
    }
}
