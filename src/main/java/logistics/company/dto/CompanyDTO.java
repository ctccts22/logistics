package logistics.company.dto;

import logistics.company.entity.Company;
import lombok.Data;

@Data
public class CompanyDTO {
    private Long companyId;
    private String companyName;
    private Company.CompanyType companyType;
    private String companyLicense;
    private String companyAddress;
    private String companyIsDeleted;
    private String companyContent;
}
