package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Service;
import com.zuehlke.securesoftwaredevelopment.repository.ServiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/scheduled-services")
    public String showService(@RequestParam(value = "columns", required = false, defaultValue = "firstName") String columns, Model model) {
        List<Service> scheduledServices = serviceRepository.getScheduled(columns);
        String[] c = columns.split(",");
        model.addAttribute("columns", c);
        model.addAttribute("scheduledServices", scheduledServices);
        return "scheduled-services";
    }
}
