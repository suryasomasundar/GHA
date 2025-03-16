package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * Class to download project mobile application file
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class FileDownloader {

    public static File downloadFile(String fileUrl, File outputDir, String fileName) throws Exception {
        File outputFile = new File(outputDir, fileName);
        System.out.println("Downloading file from: " + fileUrl);
        FileUtils.copyURLToFile(new URL(fileUrl), outputFile);

        // Verify the file was downloaded
        if (!outputFile.exists() || outputFile.length() == 0) {
            throw new RuntimeException("Failed to download the file.");
        }
        System.out.println("File downloaded successfully: " + outputFile.getAbsolutePath());

        return outputFile;
    }
}
