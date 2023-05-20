package logistics.company.repository;

import logistics.company.entity.Company;
import logistics.company.entity.QCompany;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CompanyCustomRepositoryImpl extends QuerydslRepositorySupport implements CompanyCustomRepository {

    private static final QCompany qCompany = QCompany.company;

    public  CompanyCustomRepositoryImpl() {
        super(Company.class);
    }


    @Override
    public Company findByCompanyId(Long companyId) {
        return from(qCompany)
                .select(qCompany)
                .where(qCompany.companyId.eq(companyId))
                .fetchOne();
    }

    @Override
    public Company findByCompanyName(String companyName) {
        return from(qCompany)
                .select(qCompany)
                .where(qCompany.companyName.eq(companyName))
                .fetchOne();
    }
}
