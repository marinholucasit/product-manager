package br.com.lm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DataDirectoryInitializer implements ApplicationRunner {
    @Value("${product.data.files.directory}")
    private String dataFilesDirectory;

    @Value("${product.data.auto-create-dir:false}")
    private boolean autoCreateDir;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (autoCreateDir) {
            Path path = Paths.get(dataFilesDirectory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("Diretório de dados criado: " + path.toAbsolutePath());
            }
        }
    }

}
