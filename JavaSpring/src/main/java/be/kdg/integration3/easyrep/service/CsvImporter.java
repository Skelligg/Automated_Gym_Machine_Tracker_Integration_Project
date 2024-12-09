//package be.kdg.integration3.easyrep.service;
//
//import be.kdg.integration3.easyrep.model.Arduino;
//import be.kdg.integration3.easyrep.model.Gym;
//import be.kdg.integration3.easyrep.model.Machine;
//import be.kdg.integration3.easyrep.service.session.ExerciseService;
//import be.kdg.integration3.easyrep.service.session.SessionService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.format.DateTimeFormatter;
//
//@Service
//public class CsvImporter implements CommandLineRunner {
//
//    private static final Logger log = LoggerFactory.getLogger(CsvImporter.class);
//    private final SessionService sessionService;
//    private final ExerciseSetService exerciseSetService;
//    private final ExerciseService exerciseService;
//    private final ExerciseListService exerciseListService;
//    private final MachineService machineService;
//    private final GymService gymService;
//    private final ArduinoService arduinoService;
//
//    @Autowired
//    public CsvImporter(SessionService sessionService, ExerciseSetService exerciseSetService,
//                       ExerciseService exerciseService, ExerciseListService exerciseListService,
//                       MachineService machineService, GymService gymService, ArduinoService arduinoService) {
//        this.sessionService = sessionService;
//        this.exerciseSetService = exerciseSetService;
//        this.exerciseService = exerciseService;
//        this.exerciseListService = exerciseListService;
//        this.machineService = machineService;
//        this.gymService = gymService;
//        this.arduinoService = arduinoService;
//    }
//
//    private final String path = "JavaSpring/src/main/resources/hevyData/philipeHevy.csv";
//
//    public void readingFile() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
//            String line = bufferedReader.readLine();
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//
//                // Extract and parse each column
////                LocalDateTime startTime = LocalDateTime.parse(info[0].replace("\"", ""), formatter);
////                LocalDateTime endTime = LocalDateTime.parse(info[1].replace("\"", ""), formatter);
////                String exerciseTitle = info[2].replace("\"", "").trim();
////                int setIndex = Integer.parseInt(info[3].replace("\"", "").trim());
////                double weightKg = Double.parseDouble(info[4].replace("\"", "").trim());
////                int reps = Integer.parseInt(info[5].replace("\"", "").trim());
//
//
//
//                //EXECUTE WHEN ADDING NEW EXERCISES FROM HEVY
////                // Check and add exercise if not present
////                if (exerciseListService.getExerciseByName(exerciseTitle) == null) {
////                    ExerciseList exerciseCreated = new ExerciseList();
////                    exerciseCreated.setName(exerciseTitle);
////                    try {
////                        exerciseListService.addExercise(exerciseCreated);
////                    } catch (DataIntegrityViolationException e) {
////                        System.out.println("Duplicate exercise detected: " + exerciseTitle);
////                    }
////                }
//
//
//                //creating the machines as well and giving gym 1 the ownership
////                int count=20;
////                if (machineService.findMachineByName(exerciseTitle) == null) {
////                    Arduino arduino = new Arduino();
////                    arduino.setIpAddress("0" + count);
////                    count++;
////                    arduinoService.createArduino(arduino);
//////
////                    Gym gym = gymService.findGymById(1);
////
////                    Machine machine = new Machine();
////                    machine.setName(exerciseTitle);
////                    machine.setGym(gym); // Ensure this returns a valid Gym object
////                    machine.setArduinoId(arduinoService.getLastArduino()); // Now set the Arduino after it's persisted
////                    machineService.createMachine(machine); // Persist the Machine
////                }
//
//
//
//
//
//
//
//
////                System.out.println(startTime + " end time:" + endTime);
//
//            }
//
//        } catch (FileNotFoundException ex) {
//            System.err.println("File not found: " + path);
//            ex.printStackTrace();
//        } catch (IOException e) {
//            System.err.println("Error reading the file: " + path);
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run(String... args) {
//        readingFile();
//    }
//}
