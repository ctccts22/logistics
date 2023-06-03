package logistics.vehicle.controller;

import logistics.vehicle.dto.VehicleDTO;
import logistics.vehicle.entity.Vehicle;
import logistics.vehicle.repository.VehicleRepository;
import logistics.vehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;

    @GetMapping("/vehicleView")
    public String showVehicleView(Model model) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/vehicle_view";
    }

    @PostMapping("/addVehicleView")
    @ResponseBody
    public String addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        log.info("vehicleDTO :{}", vehicleDTO);
        vehicleService.addVehicleView(vehicleDTO);
        return "redirect:/vehicle/vehicleView";
    }
    @PostMapping("/updateVehicleView")
    @ResponseBody
    public String updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        log.info("vehicleDTO :{}", vehicleDTO);
        vehicleService.updateVehicleView(vehicleDTO);
        return "redirect:/vehicle/vehicleView";
    }
}
