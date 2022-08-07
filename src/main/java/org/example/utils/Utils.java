package org.example.utils;

import org.example.models.OfficeFriends;
import org.example.models.OfficialRecipient;
import org.example.models.PersonalRecipient;
import org.example.models.Recipient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {
    private List<Recipient> recipients;
    File file = new File("C:\\Users\\Manura Sanjula\\Desktop\\Ravini\\Data.txt");

    public Utils(){
        recipients = new ArrayList<>();
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public List<Recipient> getAllRecipients() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] arrOfStr = st.split(",", 4);

                if (arrOfStr.length == 3) {
                    OfficialRecipient recipient = new OfficialRecipient();
                    recipient.setName(arrOfStr[0]);
                    recipient.setEmail(arrOfStr[1]);
                    recipient.setDesignation(arrOfStr[2]);

                    recipients.add(recipient);

                } else if (arrOfStr.length == 4) {
                    String regex = "^(.+)@(.+)$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(arrOfStr[1]);

                    if(matcher.matches()){
                        OfficeFriends recipient = new OfficeFriends();
                        recipient.setName(arrOfStr[0]);
                        recipient.setEmail(arrOfStr[1]);
                        recipient.setDesignation(arrOfStr[2]);
                        Date date = new SimpleDateFormat("yyyy/MM/dd").parse(arrOfStr[3]);
                        recipient.setBirthday(date);

                        recipients.add(recipient);
                    }else {
                        PersonalRecipient recipient = new PersonalRecipient();
                        recipient.setName(arrOfStr[0]);
                        recipient.setNickName(arrOfStr[1]);
                        recipient.setEmail(arrOfStr[2]);
                        Date date = new SimpleDateFormat("yyyy/MM/dd").parse(arrOfStr[3]);
                        recipient.setBirthday(date);

                        recipients.add(recipient);

                    }
                }
            }

            return recipients;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addRecipientToFile(String str){
        try{

            Files.write(file.toPath(), (str + "\n").getBytes(UTF_8),
                    StandardOpenOption.CREATE,StandardOpenOption.APPEND);

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
