package be.kdg.integration3.easyrep.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.stream.Stream;

@Service
public class TensorFlowService {
    private final SavedModelBundle model;
    private Logger logger = LoggerFactory.getLogger(TensorFlowService.class);


    public TensorFlowService(ResourceLoader resourceLoader) throws IOException {
        // Upload the model folder to the classpath
        Resource modelResource = resourceLoader.getResource("classpath:dataAI/model_regression");

        // Create a temporal directory for the model
        Path tempDir = Files.createTempDirectory("tf_model");

        // Copy model files to the temporary directory
        copyResourceRecursively(modelResource, tempDir);

        // Upload directory from temporal
        String modelPath = tempDir.toString();
        logger.debug("Loading model from: " + modelPath);

        model = SavedModelBundle.load(modelPath);

        // Print operations that aare available
        model.graph().operations().forEachRemaining(op ->
                logger.debug("Operation: " + op.name())
        );
    }

    private void copyResourceRecursively(Resource resource, Path targetDir) throws IOException {
        if (!resource.exists()) {
            throw new IOException("Resource not found: " + resource);
        }

        // Absolute path of the files
        URI resourceUri = resource.getURI();

        try (FileSystem fileSystem = (resourceUri.getScheme().equals("jar")) ?
                FileSystems.newFileSystem(resourceUri, new java.util.HashMap<>()) : null) {

            Path resourcePath = Paths.get(resourceUri);
            try (Stream<Path> paths = Files.walk(resourcePath)) {
                paths.forEach(path -> {
                    try {
                        Path targetPath = targetDir.resolve(resourcePath.relativize(path).toString());
                        if (Files.isDirectory(path)) {
                            Files.createDirectories(targetPath);
                        } else {
                            Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to copy resource: " + path, e);
                    }
                });
            }
        }
    }


    public float predict(float counter, float reps, float user_id) {
        try (Tensor inputTensor = TFloat32.tensorOf(Shape.of(1, 3),
                data -> {
                    data.setFloat(counter, 0, 0);
                    data.setFloat(reps, 0, 1);
                    data.setFloat(user_id, 0, 2);
                })) {

            Tensor result = model.session().runner()
                    .feed("serving_default_keras_tensor", inputTensor)
                    .fetch("StatefulPartitionedCall_1")
                    .run()
                    .get(0);

            return ((TFloat32) result).getFloat();
        }
    }
}
