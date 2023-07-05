package logistics.company.controller;

import logistics.company.dto.CompanyDTO;
import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @GetMapping("/companyView")
    public String showCompanyViewForm(Model model) {
        List<Company> companies = companyRepository.findAll();

        Map<String, Long> companyTypeCounts = companyService.getCompanyTypeCounts();

        model.addAttribute("companyTypeCounts", companyTypeCounts);

        model.addAttribute("company", companies);
        model.addAttribute("title", "사업자관리");
        return "company/company_view";
    }

    @GetMapping("/{id}")
    public String getCompanyView(@PathVariable Long id, Model model) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();

            model.addAttribute("company", company);
            return "company/co_detail_view";
        } else {
            return "redirect:/company/companyView";
        }
    }
    @GetMapping("/addCompany")
    public String addCompanyView(Model model) {
        model.addAttribute("title", "사업자 추가");
        model.addAttribute("company", new CompanyDTO());
        return "company/add_company";
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute CompanyDTO companyDTO) {
        companyService.addCompany(companyDTO);
        return "redirect:/company/companyView";
    }

    @GetMapping("/{id}/updateCompany")
    public String updateCompanyView(@PathVariable Long id, Model model) {
        Company company = companyRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("잘못된 아이디값:" + id));
        model.addAttribute("company", company);
        return "company/update_company";
    }

    @PostMapping("/{id}/updateCompany")
    public String updateCompany(@PathVariable Long id, @ModelAttribute CompanyDTO companyDTO, RedirectAttributes redirectAttributes) {
        if (companyDTO.getCompanyName().isEmpty()
                || companyDTO.getCompanyType() == null
                || companyDTO.getCompanyLicense().isEmpty()) {

            redirectAttributes.addFlashAttribute("error", "데이터가 입력되지 않았습니다.");
            return "redirect:/company/companyView";
        } else {
            companyService.updateCompany(id, companyDTO);
            redirectAttributes.addFlashAttribute("updatedSuccess", true);
            return "redirect:/company/{id}";
        }
    }
    @PostMapping("/{id}/deleteCompany")
    @ResponseBody
    public String deleteCompany(@PathVariable Long id,
                                @RequestParam(required = false) String companyIsDeleted) {
        companyService.deleteCompany(id, companyIsDeleted);

        return "redirect:/company/companyView";
    }
}
