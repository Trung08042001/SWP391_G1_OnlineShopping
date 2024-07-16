/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.DAOAccount;
import models.Account;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

/**
 *
 * @author USER
 */
    public class SenMail {

    public String getRandom() {
        Random r = new Random();
        int num = r.nextInt(9999);
        return String.format("%04d", num);
    }

    public boolean sendEmail(Account u, String code) {
        boolean test = false;
        final String fromEmail = "donnylee2003@gmail.com";
        final String password = "zfpu qdwq hksi pjbe";  // jqjzzgkyvwjexskx   juyhcikkiuyaclsb
        String toEmmail = u.getEmail();
        Properties pr = new Properties();
        pr.setProperty("mail.smtp.host", "smtp.gmail.com");
        pr.setProperty("mail.smtp.port", "587"); //TLS
        pr.setProperty("mail.smtp.auth", "true");
        pr.setProperty("mail.smtp.starttls.enable", "true");

        //get Session
        Session session = Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }

        });

        try {
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmmail));

            mess.setSubject("Security Code");
            mess.setText("" + code + ":\n"
                    + "is your Code !"
            );
            Transport.send(mess);
            test = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static void main(String[] args) {
        SenMail sm = new SenMail();
        String code = sm.getRandom();
        DAOAccount du = new DAOAccount();
        Account u = du.getAccountById(1);
        boolean test = sm.sendEmail(u, code);
        if (test) {
            System.out.println("done");
        } else {
            System.out.println("failed");
        }
    }
}
