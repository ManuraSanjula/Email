package org.example.Service;

import org.example.Service.Impl.EmailService;
import org.example.models.OfficeFriends;
import org.example.models.PersonalRecipient;
import org.example.models.Recipient;
import org.example.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EmailServiceImpl implements EmailService {
    private Utils utils;
    private List<Recipient> recipients;

    public EmailServiceImpl(){
        utils = new Utils();
        recipients = utils.getAllRecipients();
    }

    @Override
    public Boolean addRecipients(String str) {
        return utils.addRecipientToFile(str);
    }

    @Override
    public Boolean sendEmail(String to, String subject, String content) {
       return true;
    }

    @Override
    public void printCurrentBirthDays() {
        recipientsOperation(false);
    }

    private void recipientsOperation(Boolean sendingEmail) {
        this.recipients.forEach(r->{
            if(r.toString().split(",", 4).length != 3){

                String rep = r.toString();
                String name = rep.split("\\{", 2)[1].split(",", 4)[0].split("=", 2)[1];
                String email = rep.split("\\{", 2)[1].split(",", 4)[1].split("=", 2)[1];
                String thirdValue = rep.split("\\{", 2)[1].split(",", 4)[2].split("=", 2)[1];
                String date = rep.split("\\{", 2)[1].split(",", 4)[3].split("=", 2)[1];
                date = date.split("}",2)[0];

                LocalDateTime now = LocalDateTime.now();
                Date in = new Date();
                LocalDateTime ldt = now.ofInstant(in.toInstant(), ZoneId.systemDefault());
                Date today = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                Date birthday = null;
                try {
                    birthday = (Date)formatter.parse(date);
                } catch (ParseException e) {
                    birthday = new Date();
                }
                today.setHours(0);
                today.setMinutes(0);
                today.setSeconds(0);

                if(rep.split("\\{", 2)[0].equals("OfficeFriends")){
                    OfficeFriends officeFriends = new OfficeFriends();
                    officeFriends.setName(name);
                    officeFriends.setEmail(email);
                    officeFriends.setDesignation(thirdValue);
                    officeFriends.setBirthday(birthday);

                    if(sendingEmail){
                        if(officeFriends.getBirthday().toString().equals(today.toString())){
                            String content = "Wish you a Happy Birthday " + officeFriends.getName();
                            this.sendEmail(officeFriends.getEmail(),"Happy BirthDay",content);
                        }
                    }else{
                        if(officeFriends.getBirthday().toString().equals(today.toString())){
                            System.out.println(officeFriends);
                        }
                    }

                }
                if (rep.split("\\{", 2)[0].equals("PersonalRecipient")) {
                    PersonalRecipient personalRecipient = new PersonalRecipient();
                    personalRecipient.setName(name);
                    personalRecipient.setEmail(email);
                    personalRecipient.setNickName(thirdValue);
                    personalRecipient.setBirthday(birthday);

                    if(sendingEmail){
                        if(personalRecipient.getBirthday().toString().equals(today.toString())){
                            String content = "Hugs and love on your birthday " + personalRecipient.getName();
                            this.sendEmail(personalRecipient.getEmail(),"Happy BirthDay",content);
                        }
                    }else{
                        if(personalRecipient.getBirthday().toString().equals(today.toString())){
                            System.out.println(personalRecipient);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int numberOfRecipients() {
        return recipients.size();
    }

    @Override
    public void sendBirthDayEmails() {
        recipientsOperation(true);
    }

    @Override
    public void saveToFile(Recipient recipient) {
       try{
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("o.ser")));
           oos.writeObject( recipient );
           oos.close();
       }catch (Exception e){

       }
    }
}
