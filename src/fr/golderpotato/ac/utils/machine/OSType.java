package fr.golderpotato.ac.utils.machine;

/**
 * Created by Eliaz on 24/04/2017.
 */
public enum OSType {

    WINDOWS("win"),
    OSX("mac"),
    LINUX("nix"),
    SOLARIS("sunos"),
    OTHER("other");

    private String name;

    OSType(String name){
        this.name = name;
    }

    public static OSType getOSType(){
            for(OSType osType : values()){
            if(System.getProperty("os.name").indexOf(osType.name) >= 0){
                return osType;
            }
        }
        return OTHER;
    }
}
