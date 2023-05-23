package logistics.inventory.service;

import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.inventory.dto.InventoryItemDTO;
import logistics.inventory.entity.InventoryItem;
import logistics.inventory.repository.InventoryItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService{
    private final InventoryItemRepository inventoryItemRepository;
    private final CompanyRepository companyRepository;


    @Override
    @Transactional
    public void addInventoryItem(InventoryItemDTO inventoryItemDTO) {
        Company company = companyRepository.findByCompanyName(inventoryItemDTO.getCompanyName());

        if (company == null || !Objects.equals(company.getCompanyName(), inventoryItemDTO.getCompanyName())) {
            throw new RuntimeException("Company not found");
        }

        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.setInventoryItemName(inventoryItemDTO.getInventoryItemName());
        inventoryItem.setInventoryItemDescription(inventoryItemDTO.getInventoryItemDescription());
        inventoryItem.setCompany(company);

        inventoryItemRepository.save(inventoryItem);
    }

    @Override
    @Transactional
    public void updateInventoryItem(InventoryItemDTO inventoryItemDTO) {
        InventoryItem existingInventoryItem = inventoryItemRepository.findByInventoryItemName(inventoryItemDTO.getInventoryItemName());
        if (existingInventoryItem == null) {
            throw new RuntimeException("InventoryItem not found");
        }
        existingInventoryItem.setInventoryItemName(inventoryItemDTO.getInventoryItemName());
        existingInventoryItem.setInventoryItemDescription(inventoryItemDTO.getInventoryItemDescription());

        Company company = companyRepository.findByCompanyName(inventoryItemDTO.getCompanyName());

        if (company == null || !Objects.equals(company.getCompanyName(), inventoryItemDTO.getCompanyName())) {
            throw new RuntimeException("Company not found");
        }
        existingInventoryItem.setCompany(company);

        inventoryItemRepository.save(existingInventoryItem);
    }

    @Override
    @Transactional
    public void deleteInventoryItem(Long inventoryItemId) {
        // As same as optional
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        inventoryItemRepository.delete(inventoryItem);
    }
}
