package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.service.GymService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/scanner/{username}/{routineId}")
public class ScannerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GymService gymService;

    public ScannerController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping()
    public String showScanner(@PathVariable String username,
                              @PathVariable int routineId,
                              Model model) {
        logger.info("Showing scanner");
        model.addAttribute("userRoutine", routineId);
        model.addAttribute("username", username);

        return "GymGoer/scanner";
    }

    @PostMapping()
    public String handleQrCodeScan(@PathVariable String username,
                                   @PathVariable int routineId,
                                   @RequestBody Map<String, String> payload,
                                   Model model) {
        // Extract QR code from payload
        logger.info("Handling QR code scan for routine: {} from user: {}", routineId, username);

        String qrCodeStr = payload.get("qrCode");
        if (qrCodeStr == null || qrCodeStr.isEmpty()) {
            logger.error("QR Code is empty or null");
            throw new IllegalArgumentException("Invalid QR Code");
        }

        logger.info("Received QR Code: {}", qrCodeStr);

        int qrCode;
        try {
            qrCode = Integer.parseInt(qrCodeStr);
        } catch (NumberFormatException e) {
            logger.error("QR Code is not a valid integer: {}", qrCodeStr);
            throw new IllegalArgumentException("Invalid QR Code format");
        }
        // Find gym by QR code

        Gym gym = gymService.findGymByQrCode(qrCode);

        if (gym != null) {
            logger.info("Gym found: {}", gym);

            // Construct the URL string
            return String.format(
                    "redirect:/activesession/%s/startSession?username=%s&userRoutine=%d&gymId=%d",
                    username, username, routineId, gym.getGymId()
            );
        } else {
            logger.warn("No gym found for QR Code: {}", qrCode);
            throw new IllegalArgumentException("No gym found for QR Code: " + qrCode);
        }
    }

    @PostMapping("/manual")
    public String handleManualEntry(@PathVariable String username,
                                    @PathVariable int routineId,
                                    @RequestBody Map<String, String> payload) {
        logger.info("Handling manual entry for routine: {} from user: {}", routineId, username);

        String gymIdStr = payload.get("gymId");
        if (gymIdStr == null || gymIdStr.isEmpty()) {
            logger.error("Gym ID is empty or null");
            return "Invalid Gym ID";
        }

        int gymId;
        try {
            gymId = Integer.parseInt(gymIdStr);
        } catch (NumberFormatException e) {
            logger.error("Gym ID is not a valid long integer: {}", gymIdStr);
            return "Invalid Gym ID format";
        }

        Gym gym = gymService.findGymById(gymId);
        if (gym != null) {
            logger.info("Gym found: {}", gym);

            // Construct the URL string
            return String.format(
                    "redirect:/activesession/%s/startSession?username=%s&userRoutine=%d&gymId=%d",
                    username, username, routineId, gym.getGymId()
            );
        } else {
            logger.warn("No gym found for Gym ID: {}", gymId);
            return "No gym found for Gym ID: " + gymId;
        }
    }


}
