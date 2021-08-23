package isa.pharmacy.Services;

import isa.pharmacy.Domain.Mail;

import javax.mail.MessagingException;

public interface SendMailService {
    void sendMail(Mail mail);

    void sendMailWithAttachments(Mail mail) throws MessagingException;
}