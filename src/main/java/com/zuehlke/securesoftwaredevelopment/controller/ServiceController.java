package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.ScheduleService;
import com.zuehlke.securesoftwaredevelopment.domain.Service;
import com.zuehlke.securesoftwaredevelopment.domain.ServiceTicket;
import com.zuehlke.securesoftwaredevelopment.domain.User;
import com.zuehlke.securesoftwaredevelopment.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Controller
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/scheduled-services")
    public String showServices(@RequestParam(value = "columns", required = false, defaultValue = "firstName,lastName,carModel,date") String columns, Model model) {
        List<Service> scheduledServices = serviceRepository.getScheduled(columns);
        String[] c = columns.split(",");
        model.addAttribute("columns", c);
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
        serviceRepository.insertScheduledService(user.getId(), scheduleService);
        return "redirect:/";
    }

    @GetMapping("/confirm-service-1")
    public String confirmService(HttpSession session, @RequestParam Integer id) {
        ServiceTicket serviceTicket = new ServiceTicket();
        serviceTicket.setTicketNumber(UUID.randomUUID());
        serviceTicket.setId(id);
        session.setAttribute("SERVICE_TICKET", serviceTicket);
        return "confirm-service-1";
    }

    @PostMapping("/confirm-service-2")
    public String confirmService2(HttpSession session, String time, Model model) {
        ServiceTicket serviceTicket = (ServiceTicket) session.getAttribute("SERVICE_TICKET");
        if (serviceTicket == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ticket has not been created.");
        }

        serviceTicket.setTime(time);

        model.addAttribute("serviceTicketNumber", serviceTicket.getTicketNumber());
        model.addAttribute("time", serviceTicket.getTime());
        return "confirm-service-2";
    }

    @PostMapping("/confirm-service-3")
    public String confirmService3(HttpSession session) throws SQLException {
        ServiceTicket serviceTicket = (ServiceTicket) session.getAttribute("SERVICE_TICKET");
        if (serviceTicket.getTime() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ticket does not have time defined.");
        }

        serviceRepository.updateScheduledService(serviceTicket);
        session.removeAttribute("SERVICE_TICKET");

        return "redirect:/scheduled-services";
    }
}
