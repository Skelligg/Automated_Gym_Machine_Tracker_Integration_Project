package be.kdg.integration3.easyrep.service;

import be.kdg.integration3.easyrep.model.Arduino;
import be.kdg.integration3.easyrep.model.ExerciseList;
import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.sessions.Exercise;
import be.kdg.integration3.easyrep.model.sessions.ExerciseSet;
import be.kdg.integration3.easyrep.model.sessions.Session;
import be.kdg.integration3.easyrep.service.session.ExerciseService;
import be.kdg.integration3.easyrep.service.session.SessionService;
import be.kdg.integration3.easyrep.service.users.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class CsvImporter implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CsvImporter.class);
    private final SessionService sessionService;
    private final ExerciseSetService exerciseSetService;
    private final ExerciseService exerciseService;
    private final ExerciseListService exerciseListService;
    private final MachineService machineService;
    private final GymService gymService;
    private final ArduinoService arduinoService;
    private final UserService userService;

    @Autowired
    public CsvImporter(SessionService sessionService, ExerciseSetService exerciseSetService,
                       ExerciseService exerciseService, ExerciseListService exerciseListService,
                       MachineService machineService, GymService gymService, ArduinoService arduinoService,
                       UserService userService) {
        this.sessionService = sessionService;
        this.exerciseSetService = exerciseSetService;
        this.exerciseService = exerciseService;
        this.exerciseListService = exerciseListService;
        this.machineService = machineService;
        this.gymService = gymService;
        this.arduinoService = arduinoService;
        this.userService = userService;
    }

    private final String path = "JavaSpring/src/main/resources/hevyData/philipeHevy.csv";

//    public void insertingMachinesAndArduino() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
//            String line = bufferedReader.readLine();
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//
////                 Extract and parse each column
//                LocalDateTime startTime = LocalDateTime.parse(info[0].replace("\"", ""), formatter);
//                LocalDateTime endTime = LocalDateTime.parse(info[1].replace("\"", ""), formatter);
//                String exerciseTitle = info[2].replace("\"", "").trim();
//                int setIndex = Integer.parseInt(info[3].replace("\"", "").trim());
//                double weightKg = Double.parseDouble(info[4].replace("\"", "").trim());
//                int reps = Integer.parseInt(info[5].replace("\"", "").trim());
//
//
//
////                EXECUTE WHEN ADDING NEW EXERCISES FROM HEVY
//                // Check and add exercise if not present
//                if (exerciseListService.getExerciseByName(exerciseTitle) == null) {
//                    ExerciseList exerciseCreated = new ExerciseList();
//                    exerciseCreated.setName(exerciseTitle);
//                    try {
//                        exerciseListService.addExercise(exerciseCreated);
//                    } catch (DataIntegrityViolationException e) {
//                        System.out.println("Duplicate exercise detected: " + exerciseTitle);
//                    }
//                }
//
//
////                creating the machines as well and giving gym 1 the ownership
//                int count=20;
//                if (machineService.findMachineByName(exerciseTitle) == null) {
//                    Arduino arduino = new Arduino();
//                    arduino.setIpAddress("0" + count);
//                    count++;
//                    arduinoService.createArduino(arduino);
////
//                    Gym gym = gymService.findGymById(1);
//
//                    Machine machine = new Machine();
//                    machine.setName(exerciseTitle);
//                    machine.setGym(gym); // Ensure this returns a valid Gym object
//                    machine.setArduino(arduinoService.getLastArduino()); // Now set the Arduino after it's persisted
//                    machineService.createMachine(machine); // Persist the Machine
//                }
//                System.out.println(startTime + " end time:" + endTime);
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


//public void insertingExercisesAndSessions(String path) {
//
//    List<DateTimeFormatter> FORMATTERS = List.of(
//            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.ENGLISH),
//            DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale.ENGLISH)
//    );
//
//    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
//        String line = bufferedReader.readLine(); // Skip the header
//
//        Session currentSession = null;
//        List<Exercise> exercises = new ArrayList<>(); // List of exercises for the current session
//
//        while ((line = bufferedReader.readLine()) != null) {
//            String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//
//            // Remove quotes and trim whitespace for the date strings
//            String startDateStr = info[0].replace("\"", "").trim();
//            String endDateStr = info[1].replace("\"", "").trim();
//
//            // Parse timestamps and exercise details
//            LocalDateTime startTime = parseFlexibleDate(startDateStr, FORMATTERS);
//            LocalDateTime endTime = parseFlexibleDate(endDateStr, FORMATTERS);
//
//            String exerciseName = info[2].trim();
//
//
//            // Step 1: Handle session creation if startTime or endTime changes
//            if (currentSession == null ||
//                    !currentSession.getStartSession().equals(startTime) ||
//                    !currentSession.getEndSession().equals(endTime)) {
//
//                // Persist the previous session (if applicable)
//                if (currentSession != null) {
//                    log.info("Session {} with exercises persisted.", currentSession.getSession_id());
//                }
//
//                // Create a new session
//                currentSession = new Session();
//                currentSession.setGymGoerId(userService.getGymGoerByUserId(2)); // Dynamically assign if needed
//                currentSession.setStartSession(startTime);
//                currentSession.setEndSession(endTime);
//                currentSession.setStatus("COMPLETED");
//                currentSession = sessionService.createSession(currentSession); // Save session
//
//                // Reset the exercises list for the new session
//                exercises = new ArrayList<>();
//            }
//
//            // Step 2: Handle exercise creation
//            Session finalCurrentSession = currentSession;
//            List<Exercise> finalExercises = exercises;
//            Exercise exercise = exercises.stream()
//                    .filter(e -> e.getExerciseName().equals(exerciseName))
//                    .findFirst()
//                    .orElseGet(() -> {
//                        Exercise newExercise = new Exercise();
//                        newExercise.setSession(finalCurrentSession); // Associate with current session
//                        newExercise.setExerciseName(exerciseName);
//                        newExercise.setMachine(machineService.findMachineByName(exerciseName));
//                        finalExercises.add(newExercise);
//                        return newExercise;
//                    });
//
//            // Persist the exercise if it's new
//            if (exercise.getExerciseId() == 0) {
//                exercise = exerciseService.createExercise(exercise);
//            }
//
//        }
//
//        // Final session save log
//        if (currentSession != null) {
//            log.info("Final session {} with exercises persisted.", currentSession.getSession_id());
//        }
//
//    } catch (IOException e) {
//        log.error("Error reading the file: {}", path, e);
//    } catch (Exception e) {
//        log.error("An unexpected error occurred during import", e);
//    }
//}



    private LocalDateTime parseFlexibleDate(String dateStr, List<DateTimeFormatter> formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next formatter
                log.debug("Failed to parse '{}' with format: {}", dateStr, formatter);
            }
        }
        throw new DateTimeParseException("Unable to parse date: " + dateStr, dateStr, 0);
    }

    private Exercise findExerciseInBatch(Set<Exercise> exercises, Session session, String exerciseName) {
        return exercises.stream()
                .filter(e -> e.getSession().equals(session) && e.getExerciseName().equals(exerciseName))
                .findFirst()
                .orElseGet(() -> {
                    Exercise exercise = exerciseService.findExerciseBySessionAndName(session, exerciseName);
                    if (exercise != null) exercises.add(exercise); // Add to batch
                    return exercise;
                });
    }

    private Session findSessionInBatch(Set<Session> sessions, LocalDateTime startTime, LocalDateTime endTime) {
        return sessions.stream()
                .filter(s -> s.getStartSession().equals(startTime) && s.getEndSession().equals(endTime))
                .findFirst()
                .orElseGet(() -> {
                    Session session = sessionService.findSessionByStartAndEndTime(startTime, endTime);
                    if (session != null) sessions.add(session); // Add to batch
                    return session;
                });
    }

    @Transactional
    public void insertingExerciseSet(String path) {
        List<DateTimeFormatter> FORMATTERS = List.of(
                DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale.ENGLISH)
        );

//        int processedLines = 0;
//        int maxLinesToProcess = 2; // Limit to 10 lines

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine(); // Skip the header



            Set<Session> sessions = new HashSet<>();
            Set<Exercise> exercises = new HashSet<>();

            int lineNumber = 0;

            // Skip lines until line 135 (0-indexed, so we skip 134 lines)
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                if (lineNumber < 135) {
                    continue;  // Skip this line
                }


                String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String startDateStr = info[0].replace("\"", "").trim();
                String endDateStr = info[1].replace("\"", "").trim();
                String exerciseName = info[2].trim();
                int setNumber = Integer.parseInt(info[3].trim());
                double weightCount = info[4].trim().isEmpty() ? 0 : Double.parseDouble(info[4].trim());
                int repetitionCount = Integer.parseInt(info[5].trim());

                // Parse start and end dates
                LocalDateTime startTime = parseFlexibleDate(startDateStr, FORMATTERS);
                LocalDateTime endTime = parseFlexibleDate(endDateStr, FORMATTERS);

                // Find the session associated with the set (based on date/time)
                Session session = findSessionInBatch(sessions, startTime, endTime);
                if (session == null) {
                    log.warn("No session found for start time '{}' and end time '{}'. Skipping set.", startTime, endTime);
                    continue;
                }

                // Find the exercise by name for the session
                Exercise exercise = findExerciseInBatch(exercises, session, exerciseName);
                if (exercise == null) {
                    log.warn("No exercise found for name '{}' in session ID '{}'. Skipping set.", exerciseName, session.getSession_id());
                    continue;
                }

                // Create ExerciseSet entity
                ExerciseSet exerciseSet = new ExerciseSet();
                exerciseSet.setStartTime(startTime.toLocalTime());
                exerciseSet.setEndTime(endTime.toLocalTime());
                exerciseSet.setSetNumber(setNumber);
                exerciseSet.setWeightCount(weightCount);
                exerciseSet.setRepetitionCount(repetitionCount);

                // Ensure exercise is persisted/merged first
                if (exercise.getExerciseId() == 0) {
                    exerciseService.createExercise(exercise); // Ensure it's persisted/attached to the session
                }

                exerciseSet.setExercise(exercise);  // Set the exercise before persisting

                // Persist the ExerciseSet
                exerciseSetService.createExerciseSet(exerciseSet);

//                processedLines++;
            }

        } catch (IOException e) {
            log.error("Error reading file", e);
        } catch (Exception e) {
            log.error("Unexpected error occurred while processing sets", e);
        }
    }


// original
//    public void insertingExerciseSet(String path) {
//        List<DateTimeFormatter> FORMATTERS = List.of(
//                DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.ENGLISH),
//                DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale.ENGLISH)
//        );
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
//            String line = bufferedReader.readLine(); // Skip the header
//
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//
//                // Extract session details
//                String startDateStr = info[0].replace("\"", "").trim();
//                String endDateStr = info[1].replace("\"", "").trim();
//                String exerciseName = info[2].trim();
//                int setNumber = Integer.parseInt(info[3].trim());
//                double weightCount = Double.parseDouble(info[4].trim());
//                int repetitionCount = Integer.parseInt(info[5].trim());
//
//                // Parse start and end dates
//                LocalDateTime startTime = parseFlexibleDate(startDateStr, FORMATTERS);
//                LocalDateTime endTime = parseFlexibleDate(endDateStr, FORMATTERS);
//
//                // Retrieve the session by start and end time
//                Session session = sessionService.findSessionByStartAndEndTime(startTime, endTime);
//                if (session == null) {
//                    log.warn("No session found for start time '{}' and end time '{}'. Skipping set.", startTime, endTime);
//                    continue;
//                }
//
//                // Retrieve the exercise by session and name
//                Exercise exercise = exerciseService.findExerciseBySessionAndName(session, exerciseName);
//                if (exercise == null) {
//                    log.warn("No exercise found for name '{}' in session ID '{}'. Skipping set.", exerciseName, session.getSession_id());
//                    continue;
//                }
//
//                // Create a new ExerciseSet
//                ExerciseSet exerciseSet = new ExerciseSet();
//                exerciseSet.setExercise(exercise);
//                exerciseSet.setSetNumber(setNumber);
//                exerciseSet.setWeightCount(weightCount);
//                exerciseSet.setRepetitionCount(repetitionCount);
//
//                // Add the set to the exercise
//                exercise.getExerciseSets().add(exerciseSet);
//
//                // Persist the set (cascade will save it with the exercise)
//                exerciseService.updateExercise(exercise); // Use your update method to save changes
//            }
//
//            log.info("All sets have been successfully added to their respective exercises.");
//        } catch (IOException e) {
//            log.error("Error reading the file: {}", path, e);
//        } catch (Exception e) {
//            log.error("An unexpected error occurred while adding sets", e);
//        }
//    }



    @Override
    public void run(String... args) {
//        insertingExercisesAndSessions(path);
        insertingExerciseSet(path);
    }
}
