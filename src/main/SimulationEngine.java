import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class SimulationEngine implements ActionListener {
    private GrassField map;
    public final int delay;
    private Timer timer;
    private Visualization visualization;
    private MapVisualizer mapVisualizer;
    private Statistic statistic;
    Random generator= new Random();
    public SimulationEngine(GrassField map,int numberOfAnimals,int numberOfPlants,int delay) {
        this.map=map;
        this.delay=delay;
        this.map.firstAddAnimal(numberOfAnimals);
        this.map.firstAddPlants(numberOfPlants);
        this.mapVisualizer=new MapVisualizer(map);
        this.statistic=new Statistic(map);
        this.visualization=new Visualization(this.map,this,mapVisualizer,this.statistic);
        timer=new Timer(delay,this);
    }


    public void run() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()!=null) {
            if (e.getActionCommand().equals("START")){
                timer.start();
            }
            else if (e.getActionCommand().equals("STOP")){
                timer.stop();
            }
        }
        this.map.deleteDeadAnimals();
        List<Animal> listOfAnimals=this.map.getListOfAnimals();
        for (Animal animal : listOfAnimals){
            int i=generator.nextInt(32);
            animal.age++;
            animal.move(animal.getGenList()[i]);
            animal.changeEnergy(-map.moveEnergy);
        }
        this.map.eat();
        this.map.multiply();
        this.map.addPlants();
        this.statistic.newYear();
        this.visualization.update();
    }
}
