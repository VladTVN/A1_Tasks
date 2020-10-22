package com.a1.service;





import com.a1.model.Login;
import com.a1.model.Posting;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileService {

    public static String readFile(String path){
        StringBuffer readData = new StringBuffer();
        File file = new File(path);
        try {
                Scanner in = new Scanner(file);
                while(in.hasNext()){
                readData.append(in.nextLine()+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return readData.toString();
    }


    public static String[] splitOnLines(String data){
        String[] dataOnLine = data.split("\n");

        return dataOnLine;
    }


    public static String[] splitOnSemicolon(String data){
        String[] splitData = data.split(";");

        return splitData;
    }


    public static String replaceCommas(String line){
        return line.replaceAll(",",";");
    }


    public static String replaceSpace(String line){
        return line.replaceAll("\t","");
    }

    public static String replaceCommasOnDots(String line){
        return line.replaceAll(",", ".");
    }


    public static Date convertDate(String StringDate){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try {
            date = formatter.parse(StringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static Posting createModelPosting(String[] data){
        String matDoc;
        int item;
        Date docDate;
        Date pstngDate;
        String materialDescription;
        int quantity;
        String bun;
        double amountLC;
        String crcy;
        String userName;

        matDoc = data[0];
        item = Integer.parseInt(data[1]);
        docDate = convertDate(data[2]);
        pstngDate = convertDate(data[3]);
        materialDescription = data[4];
        quantity = Math.abs(Integer.parseInt(data[5]));
        bun = data[6];
        amountLC = Math.abs(Double.parseDouble(data[7]));
        crcy = data[8];
        userName = data[9];

        Posting posting =new Posting(matDoc, item, docDate, pstngDate, materialDescription, quantity, bun, amountLC, crcy, userName);

        return posting;
    }


    public static Login createModelLogin (String[] data){
        String application;
        String appAccountName;
        boolean isActive;
        String jobTitle;
        String department;

        application = data[0];
        appAccountName = data[1];
        isActive = Boolean.parseBoolean(data[2]);
        jobTitle = data[3];
        department = data[4];

        Login login = new Login(application, appAccountName, isActive, jobTitle, department);
        return login;
    }


    public static List<Posting> setAuthorizedDelivery(List<Login> loginList, List<Posting> postingList){
        for (int i = 0; i < postingList.size(); i++) {
            if(loginList.contains(postingList.get(i)) && loginList.get(i).isActive()){
                postingList.get(i).setAuthorizedDelivery(true);
            }
        }
        return postingList;
    }


    public static List<Login> parseLogins(){
        List<Login> list = new ArrayList<>();
        String readData = readFile("H:\\Games\\Downloads\\logins.csv");
        readData = replaceCommas(readData);
        readData = replaceSpace(readData);
        String[] dataOnLines = splitOnLines(readData);
        for (int i = 1; i < dataOnLines.length; i++) {
            String[] splitedData = splitOnSemicolon(dataOnLines[i]);
            list.add(createModelLogin(splitedData));
        }
        return list;
    }



    public static List<Posting> parsePostings(){
        List<Posting> list = new ArrayList<>();
        String readData = readFile("H:\\Games\\Downloads\\postings.csv");
        readData = replaceCommasOnDots(readData);
        readData = replaceSpace(readData);
        String[] dataOnLines = splitOnLines(readData);

        for (int i = 2; i < dataOnLines.length; i++) {
            String[] splitedData = splitOnSemicolon(dataOnLines[i]);
            list.add(createModelPosting(splitedData));
        }
        return list;
    }


    public static List<Posting> getPostings(List<Login> loginList){
        List<Posting> list = setAuthorizedDelivery(loginList, parsePostings());
        return list;
    }


}
