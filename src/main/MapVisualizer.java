import javax.swing.*;
import java.awt.*;

public class MapVisualizer extends JPanel {
    private GrassField map;

    public MapVisualizer(GrassField map) {
        this.map = map;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int scalex;
        int scaley;
        scalex=1000/(map.upperRightVector().x+1);
        scaley=739/(map.upperRightVector().y+1);
        super.paintComponent(g);
        this.setSize(1000,739);
        this.setLocation(0,0);
        g.setColor(Color.CYAN);
        g.fillRect(0,0,1000,739);
        g.setColor(Color.GREEN);
        for (Grass grass: map.plants.values()){
            int x=grass.getPosition().x*scalex;
            int y=grass.getPosition().y*scaley;
            g.fillRect(x,y,scalex,scaley);
        }
        for (Animal animal : map.listOfAnimals){
            int x=animal.getPosition().x*scalex;
            int y=animal.getPosition().y*scaley;
            if (animal.getEnergy()>0.9*map.startEnergy) g.setColor(Color.BLACK);
            else if (animal.getEnergy()>0.5*map.startEnergy) g.setColor(Color.DARK_GRAY);
            else if (animal.getEnergy()>0.2*map.startEnergy) g.setColor(Color.GRAY);
            else g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x,y,scalex,scaley);
        }
    }
}