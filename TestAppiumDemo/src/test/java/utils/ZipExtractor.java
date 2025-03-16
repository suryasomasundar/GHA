package utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Class to convert zip to an output application file
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class ZipExtractor {

    public static File extractZipFile(File zipFile, File outputDir) throws IOException {
        File appFile = null;
        try (ZipInputStream zipInputStream = new ZipInputStream(FileUtils.openInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                // Construct the full path for the entry
                File entryFile = new File(outputDir, entry.getName());

                // Create parent directories if they don't exist
                entryFile.getParentFile().mkdirs();

                if (entry.isDirectory()) {
                    // If the entry is a directory, create it
                    entryFile.mkdirs();
                } else {
                    // If the entry is a file, extract it
                    try (FileOutputStream outputStream = new FileOutputStream(entryFile)) {
                        IOUtils.copy(zipInputStream, outputStream);
                    }
                }

                // Check if the extracted file is part of the .app directory
                if (entry.getName().endsWith(".app/")) {
                    appFile = new File(outputDir, entry.getName());
                }
            }
        }

        // Verify that the .app directory was found
        if (appFile == null || !appFile.exists() || !appFile.isDirectory()) {
            throw new RuntimeException("Failed to extract the .app directory.");
        }

        return appFile;
    }
}
