package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.ScheduleService;
import com.zuehlke.securesoftwaredevelopment.domain.Service;
import com.zuehlke.securesoftwaredevelopment.domain.User;
import com.zuehlke.securesoftwaredevelopment.domain.Voucher;
import com.zuehlke.securesoftwaredevelopment.repository.ServiceRepository;
import com.zuehlke.securesoftwaredevelopment.repository.VoucherRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ServiceController {
    private final ServiceRepository serviceRepository;
    private final VoucherRepository voucherRepository;

    public ServiceController(ServiceRepository serviceRepository, VoucherRepository voucherRepository) {
        this.serviceRepository = serviceRepository;
        this.voucherRepository = voucherRepository;
    }

    @GetMapping("/scheduled-services")
    public String showServices(Model model) {
        List<Service> scheduledServices = serviceRepository.getScheduled();
        
        for (Service service : scheduledServices) {
            Voucher voucher = voucherRepository.findById(service.getVoucherId());
            if (voucher != null) {
                service.setVoucher(voucher.getCode());
            }
        }

        model.addAttribute("scheduledServices", scheduledServices);
        return "scheduled-services";
    }

    @GetMapping("/schedule-service")
    public String showScheduleService() {
        return "schedule-service";
    }

    @PostMapping("/schedule-service")
    public String scheduleService(ScheduleService scheduleService, Authentication authentication) throws SQLException {
        User user = (User) authentication.getPrincipal();
        Voucher voucher = this.voucherRepository.findByCode(scheduleService.getVoucher());
        Integer voucherId = voucher == null ? null : voucher.getId();
        serviceRepository.insertScheduledService(user.getId(), scheduleService, voucherId);
        return "redirect:/";
    }
}
