package logistics.company.controller;

import logistics.company.dto.CompanyDTO;
import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.company.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @GetMapping("/companyView")
    public String showCompanyViewForm(Model model) {
        List<Company> companies = companyRepository.findAll();
        model.addAttribute("company", companies);
        model.addAttribute("title", "사업자관리");
        return "company/company_view";
    }

    @PostMapping("/addCompanyView")
    public String addCompany(@ModelAttribute("company") CompanyDTO companyDTO) {
        try {
            companyService.addCompanyView(companyDTO);
            return "redirect:/company/companyView";
        } catch (RuntimeException ex) {
            return "error";
        }
    }
    @PostMapping("/updateCompanyView")
    public String updateCompany(@ModelAttribute("company") CompanyDTO companyDTO) {
        try {
            companyService.updateCompanyView(companyDTO);
            return "redirect:/company/companyView";
        } catch (RuntimeException ex) {
            return "error";
        }
    }


}
