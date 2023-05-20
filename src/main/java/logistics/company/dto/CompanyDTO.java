package logistics.company.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long companyId;
    private String companyName;
    private String companyLicense;
    private String companyAddress;
    private String companyIsDeleted;
}
