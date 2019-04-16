package de.gessnerfl.fakesmtp.controller;

import de.gessnerfl.fakesmtp.model.Email;
import de.gessnerfl.fakesmtp.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmailApiController {
    private final EmailRepository emailRepository;

    @Autowired
    public EmailApiController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @GetMapping("/email")
    public List<Email> getAllEmail() {
        return emailRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/email/{id}")
    public Optional<Email> getEmailById(@PathVariable Long id) {
        return emailRepository.findById(id);
    }

    @GetMapping("/email/to/{email}")
    public List<Email> getEmailByEmail(@PathVariable String email) {
        return emailRepository.findByToAddressOrderByIdDesc(email);
    }

    @GetMapping("/email/last")
    public Email getEmailLast() {
        List<Email> allEmails = emailRepository.findAllByOrderByIdDesc();
        return allEmails.get(0);
    }

    @GetMapping("/email/to/{email}/last")
    public Email getEmailByEmailLast(@PathVariable String email) {
        List<Email> emails = emailRepository.findByToAddressOrderByIdDesc(email);
        return emails.get(0);
    }
}
