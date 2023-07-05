package logistics.company.service;

import jakarta.persistence.EntityNotFoundException;
import logistics.company.dto.CompanyDTO;
import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void addCompany(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        companyRepository.save(company);
    }

    @Override
    @Transactional
    public void updateCompany(Long id, CompanyDTO companyDTO) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.updateWith(companyDTO);
            companyRepository.save(company);
        } else {
            throw new EntityNotFoundException("아이디를 찾을 수 없습니다 " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getCompanyTypeCounts() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .collect(Collectors.groupingBy(company -> company.getCompanyType().name(), Collectors.counting()));
    }

}
