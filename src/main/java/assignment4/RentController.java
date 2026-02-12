package assignment4;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class RentController {
    private final DatabaseManager dbManager = new DatabaseManager();

    @GetMapping
    public List<Car> getAllVehicles() {
        return dbManager.getAllVehiclesFromDb();
    }

    @PostMapping
    public String addVehicle(@RequestBody Car car) {
        boolean success = dbManager.addVehicleToDb(car);
        return success ? "Vehicle added successfully!" : "Error adding vehicle.";
    }

    @PutMapping("/{id}/rent")
    public String rentVehicle(@PathVariable int id) {
        return dbManager.rentVehicleInDb(id) ? "Vehicle rented!" : "Not found.";
    }

    @PutMapping("/{id}/return")
    public String returnVehicle(@PathVariable int id) {
        return dbManager.returnVehicleInDb(id) ? "Vehicle returned!" : "Not found.";
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable int id) {
        return dbManager.deleteVehicleFromDb(id) ? "Vehicle deleted!" : "Not found.";
    }
}