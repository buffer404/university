package Labs.Lab3.Bean;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
@Named("PaintBean")
@ApplicationScoped
public class Paint implements Serializable {
    private Canvas canvas = new Canvas();
    private String imagePath = "path/to/your/image.jpg";
    private BufferedImage myPicture;
    {
        try {
            System.out.println(123);
            myPicture = ImageIO.read (new File("resources/style/math.jpg"));
            Graphics2D g = (Graphics2D) myPicture.getGraphics();
            canvas.update(g);
        } catch (IOException e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
