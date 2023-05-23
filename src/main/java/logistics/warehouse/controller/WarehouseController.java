package logistics.warehouse.controller;

import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.warehouse.dto.WarehouseDTO;
import logistics.warehouse.entity.Warehouse;
import logistics.warehouse.repository.WarehouseRepository;
import logistics.warehouse.service.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final WarehouseRepository warehouseRepository;
    private final CompanyRepository companyRepository;

    @GetMapping("/warehouseView")
    public String showWarehouseViewForm(Model model) {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        List<Company> companies = companyRepository.findAll();

        List<String> companyNames = companies
                .stream()
                .map(Company::getCompanyName)
                .collect(Collectors.toList());

        model.addAttribute("warehouse", warehouses);
        model.addAttribute("companyNames", companyNames);
        model.addAttribute("title", "창고관리");
        return "warehouse/warehouse_view";
    }

    @PostMapping("/addWarehouseView")
    public String addWarehouse(@ModelAttribute("warehouse") WarehouseDTO warehouseDTO) {
        warehouseService.addWarehouseView(warehouseDTO);
        return "redirect:/warehouse/warehouseView";
    }
    @PostMapping("/updateWarehouseView")
    public String updateWarehouse(@ModelAttribute("warehouse") WarehouseDTO warehouseDTO) {
        warehouseService.updateWarehouseView(warehouseDTO);
        return "redirect:/warehouse/warehouseView";
    }

    @PostMapping("/deleteWarehouseView")
    public String deleteWarehouse(@RequestParam Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
        return "redirect:/warehouse/warehouseView";
    }

}
