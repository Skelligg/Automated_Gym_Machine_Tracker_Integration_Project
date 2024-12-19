package be.kdg.integration3.easyrep.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TFloat32;

import java.io.File;
import java.io.IOException;

@Service
public class TensorFlowService {
    private final SavedModelBundle model;

    public TensorFlowService(ResourceLoader resourceLoader) throws IOException {
        Resource modelResource = resourceLoader.getResource("classpath:dataAI/model_regression");
        String modelPath = modelResource.getFile().getAbsolutePath();

        System.out.println("Loading model from: " + modelPath);
        model = SavedModelBundle.load(modelPath);

        // Print available operation names
        model.graph().operations().forEachRemaining(op ->
                System.out.println("Operation: " + op.name())
        );
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