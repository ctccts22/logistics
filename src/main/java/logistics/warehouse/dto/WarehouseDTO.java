package logistics.warehouse.dto;

import logistics.company.entity.Company;
import lombok.Data;

@Data
public class WarehouseDTO {
    private Long warehouseId;
    private Long companyId;
    private String companyName;
    private String location;
}
