package magicnumbers;

import java.io.FileInputStream;
import java.util.HashMap;

public class ExtensionResolver {

    private String filename;
    private String expectedExtension;
    private HashMap<String, String[]> extensionMap = createMap();

    private static HashMap<String, String[]> createMap() {
        HashMap<String, String[]> extensionMap = new HashMap<String, String[]>();
        String[] jpg = {"FF", "D8", "FF", "E0", "00", "10", "4A", "46", "49", "46", "00", "01"};
        
        
        extensionMap.put("jpg", jpg);

        return extensionMap;
    }

    public ExtensionResolver(String filename) {

        int length = filename.length();
        this.filename = filename;

        this.expectedExtension = filename.substring(length - 3, length);

    }

    public boolean isExtensionValid() {

    }
    
}
