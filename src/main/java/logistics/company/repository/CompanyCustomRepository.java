package logistics.company.repository;

import logistics.company.entity.Company;

import java.util.List;

public interface CompanyCustomRepository {

    Company findByCompanyName(String companyName);

    Company findByCompanyId(Long companyId);


}
