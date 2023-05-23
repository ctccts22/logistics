package logistics.warehouse.service;

import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.inventory.entity.InventoryItem;
import logistics.warehouse.dto.WarehouseDTO;
import logistics.warehouse.entity.Warehouse;
import logistics.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    @Transactional
    public void addWarehouseView(WarehouseDTO warehouseDTO) {

        Company company = companyRepository.findByCompanyName(warehouseDTO.getCompanyName());
        // null-point exception방지 -> Object
        if (company == null || !Objects.equals(company.getCompanyName(), warehouseDTO.getCompanyName())) {
            throw new RuntimeException("Company not found");
        }

        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouseId(warehouseDTO.getWarehouseId());
        warehouse.setCompany(company);
        warehouse.setLocation(warehouseDTO.getLocation());

        warehouseRepository.save(warehouse);
    }

    @Override
    @Transactional
    public void updateWarehouseView(WarehouseDTO warehouseDTO) {
        Warehouse existingWarehouse = warehouseRepository.findByWarehouseId(warehouseDTO.getWarehouseId());
        if (existingWarehouse == null) {
            throw new RuntimeException("Warehouse not found");
        }
        existingWarehouse.setLocation(warehouseDTO.getLocation());
        warehouseRepository.save(existingWarehouse);
    }

    @Override
    @Transactional
    public void deleteWarehouse(Long warehouseId) {
        // As same as optional
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("warehouse item not found"));

        warehouseRepository.delete(warehouse);
    }
}
