package com.file.filehandling.service;

import com.file.filehandling.entity.FileData;
import com.file.filehandling.repository.FileRepository;
import com.file.filehandling.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public String uploadFile(MultipartFile file) throws IOException {
        FileData fileData = fileRepository.save(FileData.builder().name(file.getOriginalFilename()).
                type(file.getContentType()).fileData(FileUtils.compressFile(file.getBytes())).
                build());

        if(fileData != null) {
            return "File uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadFile(String fileName) {
        Optional<FileData> dbFileData =  fileRepository.findByName(fileName);
        byte[] files = FileUtils.decompressFile(dbFileData.get().getFileData());
        return files;
    }
}
