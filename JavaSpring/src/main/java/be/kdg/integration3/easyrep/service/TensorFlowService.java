package be.kdg.integration3.easyrep.service;

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

    public TensorFlowService(ResourceLoader resourceLoader) throws IOException {
        // Carrega la carpeta del model des del classpath
        Resource modelResource = resourceLoader.getResource("classpath:dataAI/model_regression");

        // Crea un directori temporal per al model
        Path tempDir = Files.createTempDirectory("tf_model");

        // Copia tots els fitxers del model a la carpeta temporal
        copyResourceRecursively(modelResource, tempDir);

        // Carrega el model des del directori temporal
        String modelPath = tempDir.toString();
        System.out.println("Loading model from: " + modelPath);

        model = SavedModelBundle.load(modelPath);

        // Imprimeix els noms de les operacions disponibles
        model.graph().operations().forEachRemaining(op ->
                System.out.println("Operation: " + op.name())
        );
    }

    private void copyResourceRecursively(Resource resource, Path targetDir) throws IOException {
        if (!resource.exists()) {
            throw new IOException("Resource not found: " + resource);
        }

        // Camí absolut dins del JAR o sistema de fitxers
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
