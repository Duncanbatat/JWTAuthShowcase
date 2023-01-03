package ru.artdy.RestTestTask.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.artdy.RestTestTask.dto.PersonDTO;
import ru.artdy.RestTestTask.models.Person;
import ru.artdy.RestTestTask.security.JWTUtil;
import ru.artdy.RestTestTask.services.RegistrationService;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(RegistrationService registrationService, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody PersonDTO personDTO) {
        Person person = convertToPerson(personDTO);
        registrationService.register(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Collections.singletonMap("jwt-token", token);
    }

    public Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
