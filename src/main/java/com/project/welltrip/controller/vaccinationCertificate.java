package com.project.welltrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class vaccinationCertificate {
    @GetMapping("/vaccinationCertificate")
    public String vaccinationCertificate(){
        return "vaccinationCertificate";
    }
}
