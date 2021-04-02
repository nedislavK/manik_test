package ckan_har;

import java.io.InputStream;
import java.io.OutputStream;

public class Environment  implements Runnable{
    private static String host = "192.168.0.180"; //IP address or hostname
    private static String userName = "root";      //Username
    private static String password = "1";       //User password

    @Override
    public void run() {

        try {
            String evn = ("C:/Program Files/PuTTY/plink -v " + host + " -l " + userName + " -pw " + password);
            Runtime r = Runtime.getRuntime();
            Process e = r.exec(evn);
            InputStream std = e.getInputStream();
            OutputStream out = e.getOutputStream();
            InputStream err = e.getErrorStream();


            out.write(". /usr/lib/ckan/default/bin/activate \n".getBytes());
            out.flush();
            Thread.sleep(3000);

            int value = 0;
            String otherString = "";
            if (std.available() > 0) {
                value = std.read();
                otherString = String.valueOf((char) value);
                while (std.available() > 0) {
                    value = std.read();
                    otherString += String.valueOf((char) value);
                }
            }

            int count = 0;
            String[] lines = otherString.split("\r\n|\r|\n");
            for (String string : lines) {
                System.out.println(string + " :" + count++);
            }
            e.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }
