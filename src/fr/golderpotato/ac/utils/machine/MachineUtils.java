package fr.golderpotato.ac.utils.machine;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

/**
 * Created by Eliaz on 24/04/2017.
 */
public class MachineUtils {

    private static OSType os = OSType.getOSType();
    private static Runtime runtime = Runtime.getRuntime();


    public static void executeCommand(String command)throws Exception{
        runtime.exec(command);
    }

    public static String executeCommandWithOutput(String command)throws Exception{
        Process p = runtime.exec(command);
        p.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder out = new StringBuilder();
        String line = "";
        while((line = reader.readLine()) != null){
            out.append(line+"\n");
        }
        return out.toString();
    }


    public static void shutdown()throws Exception{
        if(os == OSType.WINDOWS){
            runtime.exec("shutdown -s");
        }else if(os == OSType.LINUX || os == OSType.OSX){
            runtime.exec("shutdown -h now");
        }
    }

    public static void stop()throws Exception{
        if(os == OSType.WINDOWS){
            runtime.exec("shutdown -r");
        }else if(os == OSType.LINUX || os == OSType.OSX){
            runtime.exec("reboot");
        }
    }

    public static String getMachineName()throws Exception{
       return executeCommandWithOutput("hostname");
    }
}
