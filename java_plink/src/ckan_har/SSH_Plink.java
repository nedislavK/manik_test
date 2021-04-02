package ckan_har;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SSH_Plink implements Runnable {
    private static String host = "192.168.0.180"; //IP address or hostname
    private static String userName = "root";      //Username
    private static String password = "1";       //User password

    @Override
    public void run() {
        try {
            String command = "C:/Program Files/PuTTY/plink -v " + host + " -l " + userName + " -pw " + password;
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command);
            Thread.sleep(3000);
            InputStream std = p.getInputStream();
            OutputStream out = p.getOutputStream();
            InputStream err = p.getErrorStream();


            out.write("ls -la \n".getBytes());
            out.flush();
            Thread.sleep(3000);

            int value = 0;
            String otherString = null;
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
            p.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
