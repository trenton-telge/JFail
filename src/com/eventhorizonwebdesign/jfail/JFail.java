package com.eventhorizonwebdesign.jfail;

import com.eventhorizonwebdesign.jfail.ui.ErrorFrame;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by Trenton on 11/25/2015.
 */
public class JFail {
    public static void showErrorDialog(Exception exception, boolean isFatal){
        if (isFatal){
            new ErrorFrame(true, "Fatal Error", "A fatal error has occurred.\nYou will now be returned to your desktop.");
            try{Thread.sleep(10000);}catch(InterruptedException e){System.exit(0);}
            System.exit(0);
        } else {
            ErrorFrame f = new ErrorFrame(false, "Non-Fatal Error", "A non-fatal error has occurred.\nSome unpredictable behaviour may be noted.");
            try{Thread.sleep(10000);}catch(InterruptedException e){f.dispose();}
            f.dispose();
        }
    }
    public static void printErrorReport(Exception exception, String path, boolean full){
        String stackTrace = Arrays.toString(exception.getStackTrace());
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        File errorFile = new File(path + System.getProperty("file.separator") + strDate + ".error" + ".txt");
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(errorFile, "UTF-8");
        } catch (Exception e) {
            System.exit(0);
        }

        writer.println("Error Report");
        writer.println("");
        writer.println(strDate);
        if (full){
            writer.println(System.getProperty("user.home"));
            writer.println(System.getProperty("os.name"));
            writer.println(System.getProperty("os.version"));
            writer.println(System.getProperty("os.arch"));
            writer.println(System.getProperty("java.version"));
            writer.println(System.getProperty("java.home"));
        }
        writer.println(stackTrace);
        writer.close();
    }
    public static void sendErrorReport(Exception exception, String to, String from, String programName) throws javax.mail.MessagingException {
            String host = "localhost";
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Error From " + programName);
        message.setText(Arrays.toString(exception.getStackTrace()));
        Transport.send(message);
    }
}
