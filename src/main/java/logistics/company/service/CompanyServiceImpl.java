package logistics.company.service;

import logistics.company.dto.CompanyDTO;
import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void addCompanyView(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyLicense(companyDTO.getCompanyLicense());
        company.setCompanyAddress(companyDTO.getCompanyAddress());

        companyRepository.save(company);
    }

    @Override
    public void updateCompanyView(CompanyDTO companyDTO) {
        Company existingCompany = companyRepository.findByCompanyId(companyDTO.getCompanyId());
        if (existingCompany == null) {
            throw new RuntimeException("Company not found");
        }

        existingCompany.setCompanyName(companyDTO.getCompanyName());
        existingCompany.setCompanyLicense(companyDTO.getCompanyLicense());
        existingCompany.setCompanyAddress(companyDTO.getCompanyAddress());
        existingCompany.setCompanyIsDeleted(companyDTO.getCompanyIsDeleted());

        companyRepository.save(existingCompany);
    }
}
