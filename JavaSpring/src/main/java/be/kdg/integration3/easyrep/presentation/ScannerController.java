package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.Gym;
import be.kdg.integration3.easyrep.service.GymService;
import be.kdg.integration3.easyrep.service.routines.MachineService;
import be.kdg.integration3.easyrep.service.routines.RoutineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/scanner")
public class ScannerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GymService gymService;

    public ScannerController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("/scan")
    public String showScanner() {
        logger.info("Showing scanner");
        return "GymGoer/scanner";
    }

    @PostMapping("/scan")
    public ResponseEntity<String> handleQrCodeScan(@RequestBody Map<String, String> payload) {
        String qrCode = payload.get("qrCode");
        if (qrCode == null || qrCode.isEmpty()) {
            logger.error("QR Code is empty or null");
            return ResponseEntity.badRequest().body("Invalid QR Code");
        }

        logger.info("Received QR Code: {}", qrCode);

        Gym gym = gymService.findGymByQrCode(qrCode);

        if (gym != null) {
            logger.info("Gym found: {}", gym);
            return ResponseEntity.ok("Gym found: " + gym.getName());
        } else {
            logger.warn("No gym found for QR Code: {}", qrCode);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No gym found for QR Code: " + qrCode);
        }
    }
}
