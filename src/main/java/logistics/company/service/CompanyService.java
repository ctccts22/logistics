package logistics.company.service;

import logistics.company.dto.CompanyDTO;

import java.util.Map;

public interface CompanyService {
    void addCompany(CompanyDTO companyDTO);
    void updateCompany(Long id, CompanyDTO companyDTO);
    Map<String, Long> getCompanyTypeCounts();
    void deleteCompany(Long id, String companyIsDeleted);
}
