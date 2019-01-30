package magicnumbers;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExtensionResolver {

    private String filename;
    private String expectedExtension;
    private HashMap<String, String[]> extensionMap = createMap();

    // https://en.wikipedia.org/wiki/Magic_number_(programming)
    // it's enough to check just 2 bytes as there's no two different extensions with exactly same beginning
    private static HashMap<String, String[]> createMap() {
        HashMap<String, String[]> extensionMap = new HashMap<String, String[]>();
        // JPEG image files begin with FF D8 and end with FF D9. JPEG/JFIF files contain the ASCII code for "JFIF" (4A 46 49 46) as a null terminated string.
        String[] jpg = {"ff", "d8"};
        // The UTF-8 representation of the BOM is the (hexadecimal) byte sequence 0xEF,0xBB,0xBF.
        String[] txt = {"ef", "bb"};
        // GIF image files have the ASCII code for "GIF89a" (47 49 46 38 39 61) or "GIF87a" (47 49 46 38 37 61)
        String[] gif = {"47", "49"};

        extensionMap.put("jpg", jpg);
        extensionMap.put("txt", txt);
        extensionMap.put("gif", gif);

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
            
            if (magicNumberOfTestFile == null) {
                System.out.println("Unsupported Extension");
                return false;
            }

            String actualMagicNumberOfFile = "";
            boolean notMatch = false;
            for (int i = 0; i < magicNumberOfTestFile.length; i++) {
                int currentFilebyte = testFile.read();

                String hexValueOfByte = Integer.toHexString(currentFilebyte);
                actualMagicNumberOfFile += hexValueOfByte + " ";
                if (!hexValueOfByte.equals(magicNumberOfTestFile[i])) {
                    notMatch = true;
                }
            }
            
            if (notMatch) {
                String actualExtension = this.getActualExtension(actualMagicNumberOfFile.trim());
                System.out.println("Extension is " + this.expectedExtension + ", while actually it's " + actualExtension + ".");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        }
        return true;
    }

    public FileInputStream readFile() throws Exception {
        URL url = getClass().getResource(this.filename);
        File file = new File(url.getPath());
        return new FileInputStream(file);
    }

    public String getActualExtension(String magicNumber) {
        Iterator it = this.extensionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String tempMagicNumber = String.join(" ", (String[])pair.getValue());
            if (magicNumber.equals(tempMagicNumber)) {
                return (String)pair.getKey();
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        return "unsupported";
    }
}
