package magicnumbers;

public class MagicNumbers {

    public static void main(String[] args) {
       
        ExtensionResolver erTxt = new ExtensionResolver("test.txt");
        if (erTxt.isExtensionValid()) {
            System.out.println("Extension is valid");
        }
        
        ExtensionResolver erJpg = new ExtensionResolver("test.jpg");
        if (erJpg.isExtensionValid()) {
            System.out.println("Extension is valid");
        }
        ExtensionResolver erGif = new ExtensionResolver("test.gif");
        if (erGif.isExtensionValid()) {
            System.out.println("Extension is valid");
        }
        
        ExtensionResolver erInvalidGif = new ExtensionResolver("invalid_gif.gif");
        if (erInvalidGif.isExtensionValid()) {
            System.out.println("Extension is valid");
        }
        
        
    }
    
}
