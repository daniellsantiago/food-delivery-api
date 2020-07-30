package com.daniellsantiago.fooddeliveryapi.infrastructure.service.storage;

import com.daniellsantiago.fooddeliveryapi.domain.service.PhotoStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageService implements PhotoStorageService {

    @Value("${food-delivery.storage.local.photo-directory}")
    private Path photoDirectory;

    @Override
    public InputStream recovery(String filename) {
        try {
            Path path = getFilePath(filename);

            return Files.newInputStream(path);
        } catch (Exception e) {
            throw new StorageException("Couldn't recovery file.");
        }
    }

    @Override
    public void storage(NewPhoto newPhoto) {
        try {
            Path filePath = getFilePath(newPhoto.getFilename());
            FileCopyUtils.copy(newPhoto.getInputStream(),
                    Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Could not store the file.");
        }
    }

    @Override
    public void remove(String filename) {
        try {
            Path filePath = getFilePath(filename);

            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Couldn't delete the file.");
        }
    }

    private Path getFilePath(String filename) {
        return photoDirectory.resolve(Path.of(filename));
    }
}
