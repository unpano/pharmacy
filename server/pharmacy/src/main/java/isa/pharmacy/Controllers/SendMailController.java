package isa.pharmacy.Controllers;

import isa.pharmacy.Domain.Mail;
import isa.pharmacy.Services.SendMailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email/")
public class SendMailController {
    SendMailService service;

    public SendMailController(SendMailService service) {
        this.service = service;
    }

    @PostMapping("/send")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> sendMail(@RequestBody Mail mail) {
        service.sendMail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail) throws MessagingException {
        service.sendMailWithAttachments(mail);
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}

