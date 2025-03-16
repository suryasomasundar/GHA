package utils;

import java.io.File;

/**
 * Class to clean resources folder (optional)
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class ResourceCleaner {

    public static void main(String[] args) {
        // Path to the resources directory
        File resourcesDir = new File("src/test/resources");

        // Delete all contents of the resources directory
        if (cleanResourcesDirectory(resourcesDir)) {
            System.out.println("All contents of the resources directory have been deleted.");
        } else {
            System.err.println("Failed to delete contents of the resources directory.");
        }
    }

    /**
     * Deletes all files and subdirectories within the specified directory.
     *
     * @param directory The directory to clean.
     * @return true if the operation was successful, false otherwise.
     */
    public static boolean cleanResourcesDirectory(File directory) {
        if (!directory.exists()) {
            System.out.println("Resources directory does not exist: " + directory.getAbsolutePath());
            return false;
        }

        // Get all files and subdirectories
        File[] files = directory.listFiles();
        if (files == null) {
            return false; // Directory is not accessible
        }

        // Delete each file/subdirectory
        for (File file : files) {
            if (file.isDirectory()) {
                cleanResourcesDirectory(file); // Recursively delete subdirectories
            }
            if (!file.delete()) {
                System.err.println("Failed to delete: " + file.getAbsolutePath());
                return false;
            }
        }

        return true;
    }

}
