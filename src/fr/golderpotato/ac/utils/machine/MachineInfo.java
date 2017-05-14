package fr.golderpotato.ac.utils.machine;

/**
 * Created by Eliaz on 24/04/2017.
 */
public class MachineInfo {

    private static String machineName;

    public static String getMachineName()throws Exception{
        if (machineName == "") {
            machineName = MachineUtils.getMachineName();
            return machineName;
        }else{
            return machineName;
        }
    }
}
