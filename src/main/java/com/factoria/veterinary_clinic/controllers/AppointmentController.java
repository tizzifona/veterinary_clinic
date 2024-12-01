package com.factoria.veterinary_clinic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factoria.veterinary_clinic.dtos.AppointmentDto;
//import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.models.Appointment;
import com.factoria.veterinary_clinic.services.AppointmentService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path="/appointments")
public class AppointmentController {
    private AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppointmentDto> index() {
        return service.findAll().stream()
        .map(appointment -> new AppointmentDto(
                          appointment.getPatient() != null ? appointment.getPatient().getId() : null,
                          appointment.getPatient() != null ? appointment.getPatient().getName() : null,
                          appointment.getAppointmentDateTime(),
                          appointment.getType(),
                          appointment.getReason(),
                          appointment.getStatus()
                      ))
                      .toList();
    }

    /*@GetMapping("appointments")
    public AppointmentDto store(@RequestBody AppointmentDto entity) {///Or maybe metod type shoud be AppointmentDto??
        return entity;
    }*/
    
}
