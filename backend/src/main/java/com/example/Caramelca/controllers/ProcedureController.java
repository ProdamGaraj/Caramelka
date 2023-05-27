package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Client.User;
import com.example.Caramelca.models.Client.Appointment;
import com.example.Caramelca.models.Client.Employee;
import com.example.Caramelca.models.Client.Procedure;
import com.example.Caramelca.services.ProcedureService;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Controller
//TODO @RequestMapping("/procedure")
public class ProcedureController {

    private final ProcedureService procedureService;


    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/procedure/{id}")
    public String procedure(@PathVariable(value = "id") Procedure procedure,
                            Model model) {
        Pair<LocalDate, LocalDate> dates = procedureService.getMinMaxDates();

        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "procedure";
    }

    @GetMapping("/procedure/{id}/filter")
    public String proceduresFilter(@PathVariable(value = "id") Procedure procedure,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                                @RequestParam(required = false) Employee employer,
                                Model model) {
        Pair<LocalDate, LocalDate> dates = procedureService.getMinMaxDates();

        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "procedure";
    }

    @PostMapping("/procedure/{id}/appointment")
    public String appointment(@PathVariable(value = "id") Procedure procedure,
                              @RequestParam String date,
                              @RequestParam String time,
                              @RequestParam Employee employer,
//                              @AuthenticationPrincipal User user) {
                              @RequestParam User user) {

        Appointment appointment = procedureService.saveAppointment(
                employer, procedure, user,
                LocalDate.parse(date),
                LocalTime.parse(time));

        return "redirect:/index";
    }

}
