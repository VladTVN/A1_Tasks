package com.company;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Main {

    public static int ipToInt(String stringIp){
        InetAddress inetAddressOrigin = null;
        try {
            inetAddressOrigin = InetAddress.getByName(stringIp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int intRepresentation = ByteBuffer.wrap(inetAddressOrigin.getAddress()).getInt();
        return intRepresentation;
    }

    public static String ipToString(int intIp ){
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(intIp);
        byte[] b = buffer.array();

        InetAddress inetAddressRestored = null;
        try {
            inetAddressRestored = InetAddress.getByAddress(b);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = inetAddressRestored.getHostAddress();
        return ip;
    }



    public static void main(String[] args) {
        int intIp;
        String stringIp;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter IP");
        stringIp = in.nextLine();
        intIp = ipToInt(stringIp);
        System.out.println(intIp);

        System.out.println("Enter Integer");
        intIp = in.nextInt();
        stringIp = ipToString(intIp);
        System.out.println(stringIp);

    }
}
