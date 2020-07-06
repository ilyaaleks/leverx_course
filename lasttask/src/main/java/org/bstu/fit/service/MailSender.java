package org.bstu.fit.service;

public interface MailSender {
    public void send(String emailTo, String subject, String message);

}
