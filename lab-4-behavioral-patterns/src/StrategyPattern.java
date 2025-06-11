interface ImageLoadStrategy {
    void load(String href);
}

class FileImageLoader implements ImageLoadStrategy {
    public void load(String href) {
        System.out.println("Loading image from file: " + href);
    }
}

class NetworkImageLoader implements ImageLoadStrategy {
    public void load(String href) {
        System.out.println("Loading image from network: " + href);
    }
}

class LightImage {
    private String href;
    private ImageLoadStrategy strategy;

    public LightImage(String href) {
        this.href = href;
        this.strategy = selectStrategy(href);
    }

    private ImageLoadStrategy selectStrategy(String href) {
        if (href.startsWith("http://") || href.startsWith("https://")) {
            return new NetworkImageLoader();
        } else {
            return new FileImageLoader();
        }
    }

    public void render() {
        strategy.load(href);
    }
}

public class StrategyPattern {
    public static void run() {
        LightImage localImage = new LightImage("C:/images/photo.png");
        LightImage webImage = new LightImage("https://example.com/photo.jpg");

        localImage.render();
        webImage.render();
    }
}
