package cubic_control.resources.assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animator {
    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;
    private BufferedImage[] images;
    private BufferedImage currentImage;

    public Animator(int Speed, BufferedImage ... args) {
        this.speed = Speed;
        this.images = new BufferedImage[args.length];
        int i = 0;
        while (i < args.length) {
            this.images[i] = args[i];
            ++i;
        }
        this.frames = args.length;
    }

    public void runAnimation() {
        ++this.index;
        if (this.index > this.speed) {
            this.index = 0;
            this.nextFrame();
        }
    }

    private void nextFrame() {
        int i = 0;
        while (i < this.frames) {
            if (this.count == i) {
                this.currentImage = this.images[i];
            }
            ++i;
        }
        ++this.count;
        if (this.count > this.frames) {
            this.count = 0;
        }
    }

    public void drawAnimation(Graphics2D g, int x, int y) {
        g.drawImage(this.currentImage, x, y, null);
    }

    public void drawAnimation(Graphics2D g, int x, int y, int ScaleX, int ScaleY) {
        g.drawImage(this.currentImage, x, y, ScaleX, ScaleY, null);
    }
}