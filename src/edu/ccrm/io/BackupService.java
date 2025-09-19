package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {
    private static final Path SOURCE_DIR = Paths.get("data");
    private static final Path BACKUP_DIR = Paths.get("backups");

    public void performBackup() throws IOException {
        // Use Date/Time API for the timestamped folder name [cite: 94]
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path backupPath = BACKUP_DIR.resolve("backup_" + timestamp);

        Files.createDirectories(backupPath);

        // Use NIO.2 Files.walk to go through the source directory 
        try (Stream<Path> stream = Files.walk(SOURCE_DIR)) {
            stream.filter(Files::isRegularFile)
                  .forEach(sourceFile -> {
                      try {
                          // Copy each file to the new backup folder 
                          Files.copy(sourceFile, backupPath.resolve(sourceFile.getFileName()));
                      } catch (IOException e) {
                          System.err.println("Failed to copy file: " + sourceFile);
                      }
                  });
        }
        System.out.println("Backup created successfully at: " + backupPath);
    }

    // Recursive utility to calculate directory size 
    public long calculateDirectorySize(Path path) throws IOException {
        // Files.walk is recursive by nature
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                .filter(Files::isRegularFile)
                .mapToLong(p -> {
                    try {
                        return Files.size(p);
                    } catch (IOException e) {
                        return 0L;
                    }
                })
                .sum();
        }
    }
}