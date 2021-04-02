package ckan_har;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Source_Creator implements Runnable{
    private static String host = "192.168.0.180"; //IP address or hostname
    private static String userName = "root";      //Username
    private static String password = "1";       //User password

    @Override
    public void run() {
        try {
            String har_source = ("C:/Program Files/PuTTY/plink -v " + host + " -l " + userName + " -pw " + password);
            Runtime r = Runtime.getRuntime();
            Process h = r.exec(har_source);
            InputStream std = h.getInputStream();
            OutputStream out = h.getOutputStream();
            InputStream err = h.getErrorStream();

            out.write(". /usr/lib/ckan/default/bin/activate \n".getBytes());
            out.write ("ckan --config=/etc/ckan/default/ckan.ini harvester source create meet_test http://192.168.0.157/dataset/3eurticket.rdf dcat_rdf meet_test true testorg \n".getBytes());
        out.flush();
        Thread.sleep(30000);

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
        h.destroy();

    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}
