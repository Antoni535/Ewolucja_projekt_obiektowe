import javax.swing.*;
import java.awt.*;

public class PrintStatistic extends JPanel {
    private Statistic statistic;
    public PrintStatistic(Statistic statistic){
        this.statistic=statistic;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        this.setSize(400,739);
        this.setLocation(1000,0);

        g.drawString("Year: "+String.valueOf(statistic.getYear()),10,10);
        g.drawString("numbers of animals: "+String.valueOf(statistic.getActualNumberOfAnimals()),10,30);
        g.drawString("numbers of plants: "+String.valueOf(statistic.getActualNumberOfPlants()),10,50);
        g.drawString("Average number of children: "+String.valueOf(statistic.getAverageNumberOfChildren()),10,70);
        g.drawString("Average energy: "+String.valueOf(statistic.getAverageEnergy()),10,90);
        g.drawString("Average life length: "+String.valueOf(statistic.getAverageLifeLength()),10,110);
    }
}
