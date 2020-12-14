package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.ScheduleService;
import com.zuehlke.securesoftwaredevelopment.domain.Service;
import com.zuehlke.securesoftwaredevelopment.domain.User;
import com.zuehlke.securesoftwaredevelopment.repository.ServiceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/scheduled-services")
    public String showServices(Model model) {
        List<Service> scheduledServices = serviceRepository.getScheduled();
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
        Integer voucherId = null; // TODO
        serviceRepository.insertScheduledService(user.getId(), scheduleService, voucherId);
        return "redirect:/";
    }
}
