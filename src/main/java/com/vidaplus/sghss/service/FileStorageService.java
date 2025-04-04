package com.vidaplus.sghss.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String salvarArquivo(MultipartFile file) {
        try {
            // Garante que o diretório existe
            Path diretorio = Paths.get(uploadDir);
            if (!Files.exists(diretorio)) {
                Files.createDirectories(diretorio);
            }

            // Gerar nome único para evitar conflitos
            String extensao = FilenameUtils.getExtension(file.getOriginalFilename());
            String nomeArquivo = UUID.randomUUID() + "." + extensao;

            // Salvar arquivo no diretório
            Path caminhoArquivo = diretorio.resolve(nomeArquivo);
            Files.copy(file.getInputStream(), caminhoArquivo);

            return nomeArquivo;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public byte[] carregarArquivo(String nomeArquivo) {
        try {
            Path caminhoArquivo = Paths.get(uploadDir).resolve(nomeArquivo);
            return Files.readAllBytes(caminhoArquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo: " + e.getMessage());
        }
    }
}
