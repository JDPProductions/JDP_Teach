package imagecrawl;

/**
 *
 * @author DimensionDetector
 */

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import org.opencv.imgproc.Imgproc;


public class ImageCrawl {

    public static void main(String[] args) throws IOException {
        //BUILDINGS 1: http://image-net.org/api/text/imagenet.synset.geturls?wnid=n02734725
        //LIGHTPOSTS 1:http://image-net.org/api/text/imagenet.synset.geturls?wnid=n04335886
        //LOLIPOPS 3: http://image-net.org/api/text/imagenet.synset.geturls?wnid=n07615774
        
        Document doc = Jsoup.connect("http://image-net.org/api/text/imagenet.synset.geturls?wnid=n04335886").get();
       
        String links = doc.body().text();
        String[] link = links.split(" ");

        //String filename = "pos.txt";
        //PrintWriter output = new PrintWriter(filename);
        System.out.println(link.length);
        for(int i = 79; i < link.length; i++){
            if(i >= 10000){
                saveImage(link[i], Integer.toString(i) + ".jpg", i);
                //saveName(Integer.toString(i) + ".jpg", output);
            }
            else if(i < 10000 && i > 1000){
                saveImage(link[i], "0" + Integer.toString(i) + ".jpg", i);
                //saveName("0" + Integer.toString(i) + ".jpg", output);
            }
            else if(i < 1000 && i >= 100){
                saveImage(link[i], "00" + Integer.toString(i) + ".jpg", i);
                //saveName("00" + Integer.toString(i) + ".jpg", output);
            }
            else if(i < 100 && i >= 10){
                saveImage(link[i], "000" + Integer.toString(i) + ".jpg", i);
                //saveName("000" + Integer.toString(i) + ".jpg", output);
            }
            else if(i < 10){
                saveImage(link[i], "0000" + Integer.toString(i) + ".jpg", i);
                //saveName("0000" + Integer.toString(i) + ".jpg", output);
            }
        }
        //output.close();
    }
    
    public static void saveName(String name, PrintWriter positives) throws FileNotFoundException{
        positives.println("positive/" + name + " 0 0 0 0 0");
    }
    
    public static void saveImage(String imageURL, String name, int num) throws IOException{ 
        try{
            URL url = new URL(imageURL);
         
            URLConnection ucon = url.openConnection();
            ucon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:7.0.1) Gecko/20100101 Firefox/7.0.1");
            
            BufferedImage image = ImageIO.read(ucon.getInputStream());
            
            if(image != null){
                
                int width = image.getWidth();
                int height = image.getHeight();
                
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                
                Mat ogImage = new Mat(height, width, CvType.CV_8UC3);
                byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
                ogImage.put(0, 0, data);
                
                imwrite("../ImageCrawl/src/images/" + name, ogImage);
                
                //ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
                //ColorConvertOp op = new ColorConvertOp(cs, null);  
                //BufferedImage gray = op.filter(image, null);
                //BufferedImage gray = image;
                
                //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                //Size newSize = new Size(width, height);
                //Mat ogImage = new Mat(height, width, CvType.CV_8SC3);
                //byte[] data = ((DataBufferByte) gray.getRaster().getDataBuffer()).getData();
                //ogImage.put(0,0,data);
                
                /*
                if(width < height){
                    
                    width = Math.round((width * 200)/ height);
                    height = 200;
                }
                else if(image.getHeight() < image.getWidth()){
                    height = Math.round((height * 200)/ width);
                    width = 200;
                }
                else{
                    //equal to each other
                    width = 200;
                    height = 200;
                }
                
                Mat resizedImage = new Mat();
                Size newSize = new Size(width, height);
                Imgproc.resize(ogImage, resizedImage, newSize);
                */
                
            }
        }catch(IOException e){}
    }    
}
