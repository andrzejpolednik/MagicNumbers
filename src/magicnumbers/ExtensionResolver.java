package magicnumbers;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
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
        try {
            FileInputStream testFile = readFile();
            String[] magicNumberOfTestFile = extensionMap.get(this.expectedExtension);
            if (magicNumberOfTestFile.length == 0){
                System.out.println("Unsupported Extension");
                return false;
            }
            
            for(int i=0;i < magicNumberOfTestFile.length;i ++){
                int currentFilebyte = testFile.read();
                System.out.println(currentFilebyte);
                //String hexValueOfByte = Integer.valueOf(String.valueOf(currentFilebyte), 16);
                //if()
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public FileInputStream readFile() throws Exception {
        URL url = getClass().getResource(this.filename);
        File file = new File(url.getPath());
        return new FileInputStream(file);
    }
}
