package br.com.lm.utils;

import br.com.lm.model.JsonProductModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import br.com.lm.model.ProductModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class JsonProductReader {
    private final ObjectMapper objectMapper;
    private final String dataFilesDirectory;

    public JsonProductReader(ObjectMapper objectMapper,
                             @Value("${product.data.files.directory}") String dataFilesDirectory) {
        this.objectMapper = objectMapper;
        this.dataFilesDirectory = dataFilesDirectory;
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<ProductModel> readProductsFromJsonFiles() throws IOException {
        Path dirPath = Paths.get(dataFilesDirectory);
        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new IOException("Directory not found: " + dataFilesDirectory);
        }

        List<Path> jsonFiles;
        try (Stream<Path> paths = Files.list(dirPath)) {
            jsonFiles = paths
                    .filter(path -> path.toString().endsWith(".json"))
                    .toList();
        } catch (IOException e) {
            log.error("Error listing files in directory: {}", dirPath, e);
            return Collections.emptyList();
        }

        if (jsonFiles.isEmpty()) {
            log.warn("No JSON files found in directory: {}", dataFilesDirectory);
            return Collections.emptyList();
        }

        return jsonFiles.parallelStream()
                .flatMap(path -> {
                    try {
                        return parseJsonFile(path).stream();
                    } catch (IOException e) {
                        log.error("Error processing file: {}", path, e);
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }

    private List<ProductModel> parseJsonFile(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath)) {
            JsonNode rootNode = objectMapper.readTree(is);

            if (!rootNode.has("data")) {
                log.warn("Invalid JSON structure in file: {}. Missing 'data' field.", filePath);
                return Collections.emptyList();
            }

            JsonNode dataNode = rootNode.get("data");
            if (!dataNode.isArray()) {
                log.warn("Invalid JSON structure in file: {}. 'data' is not an array.", filePath);
                return Collections.emptyList();
            }

            List<ProductModel> products = new ArrayList<>();
            for (JsonNode node : dataNode) {
                try {
                    ProductModel product = createProductModelFromNode(node);
                    if (product != null) {
                        products.add(product);
                    }
                } catch (Exception e) {
                    log.error("Error parsing product in file {} at node: {}", filePath, node, e);
                }
            }
            return products;
        }
    }

    private ProductModel createProductModelFromNode(JsonNode node) {

        if (!node.has("product") || !node.has("type")) {
            log.warn("Missing required fields in product node: {}", node);
            return null;
        }

        return new JsonProductModel(
                node.get("product").asText(),
                node.get("type").asText(),
                node.has("quantity") && !node.get("quantity").isNull() ? node.get("quantity").asInt() : null,
                node.has("price") ? node.get("price").asText() : null,
                node.has("industry") ? node.get("industry").asText() : null,
                node.has("origin") ? node.get("origin").asText() : null
        );
    }
}
