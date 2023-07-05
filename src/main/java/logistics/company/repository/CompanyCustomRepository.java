package logistics.company.repository;

import logistics.company.entity.Company;

public interface CompanyCustomRepository {

    Company findByCompanyName(String companyName);

    Company findByCompanyId(Long companyId);
}
