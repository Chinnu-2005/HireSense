package com.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String toEmail, String candidateName, String jobRole, String companyName) {
        String subject = "Congratulations! You've been shortlisted for " + jobRole + " at " + companyName;
        String body = String.format(
                "Dear %s,\n\n" +
                "We are pleased to inform you that your application for the position of '%s' at '%s' has been accepted by the recruiter! " +
                "You have been successfully shortlisted, and your profile is proceeding to the next round of interviews.\n\n" +
                "The hiring team will reach out to you shortly with details regarding the next steps and interview schedule.\n\n" +
                "Best regards,\n" +
                "The HireSense Hiring Team",
                candidateName, jobRole, companyName
        );

        if (mailSender != null) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(toEmail);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
                log.info("Successfully sent shortlisting email to candidate: {}", toEmail);
                return;
            } catch (Exception e) {
                log.warn("Failed to send real email via SMTP. Falling back to log display. Error: {}", e.getMessage());
            }
        }

        // Fallback display in log/console
        log.info("\n" +
                "========================================================================\n" +
                "                  AUTOMATED SHORTLISTING EMAIL TRIGGERED                \n" +
                "========================================================================\n" +
                "To: {}\n" +
                "Subject: {}\n" +
                "Content:\n{}\n" +
                "========================================================================",
                toEmail, subject, body
        );
    }
}
