package magicnumbers;

public class MagicNumbers {

    public static void main(String[] args) {
//        ExtensionResolver er = new ExtensionResolver("image.jpg");
//        er.isExtensionValid();
        
        ExtensionResolver erTxt = new ExtensionResolver("test.txt");
        if (erTxt.isExtensionValid()) {
            System.out.println("Extension is valid");
        }
        
        ExtensionResolver erJpg = new ExtensionResolver("test.jpg");
        if (erJpg.isExtensionValid()) {
            System.out.println("Extension is valid");
        }
    }
    
}
