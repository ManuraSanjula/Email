package org.example.Service.Impl;

import org.example.models.Recipient;

import java.util.List;

public interface EmailService {
    public Boolean addRecipients(String str);
    public Boolean sendEmail(String to, String subject, String content);
    public void printCurrentBirthDays();
    public int numberOfRecipients();

    public void sendBirthDayEmails();

    public void saveToFile(Recipient recipient);
}
