package com.file.filehandling.repository;

import com.file.filehandling.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String fileName);
}
