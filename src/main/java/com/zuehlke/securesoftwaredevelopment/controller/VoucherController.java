package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Voucher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Controller
public class VoucherController {
    private static final Logger LOG = LoggerFactory.getLogger(VoucherController.class);
    private static final String SESSION_VOUCHER_CODE = "voucherCode";

    @GetMapping("/create-voucher")
    public String showCreateVaucher(HttpSession session) {
        session.setAttribute(SESSION_VOUCHER_CODE, Voucher.generateCode());
        return "create-voucher";
    }

    @PostMapping("/send-voucher")
    public String sendVoucher(@RequestParam("email") String email, HttpSession session) {
        String voucherCode = (String) session.getAttribute(SESSION_VOUCHER_CODE);
        if (voucherCode == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LOG.info("Voucher code {} was sent to email {}", voucherCode, email);
        session.removeAttribute(SESSION_VOUCHER_CODE);
        return "redirect:/";
    }
}
