import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartSimulation extends JFrame implements ActionListener {
    JButton button1=new JButton("ONE MAP");
    JButton button2=new JButton("TWO MAPS");
    int width;
    int height;
    int startEnergy;
    int plantEnergy;
    int moveEnergy;
    int jungleRatio;
    int numberOfAnimals;
    int numberOfPlants;
    int delay;

    public StartSimulation(JSONObject jsonObject){
        button1.addActionListener(this);
        button2.addActionListener(this);
        button1.setActionCommand("one");
        button2.setActionCommand("two");
        width=Integer.parseInt(jsonObject.get("width").toString());
        height=Integer.parseInt(jsonObject.get("height").toString());
        startEnergy=Integer.parseInt(jsonObject.get("startEnergy").toString());
        plantEnergy=Integer.parseInt(jsonObject.get("plantEnergy").toString());
        moveEnergy=Integer.parseInt(jsonObject.get("moveEnergy").toString());
        jungleRatio=Integer.parseInt(jsonObject.get("jungleRatio").toString());
        numberOfAnimals=Integer.parseInt(jsonObject.get("numberOfAnimals").toString());
        numberOfPlants=Integer.parseInt(jsonObject.get("numberOfPlants").toString());
        delay=Integer.parseInt(jsonObject.get("delay").toString());
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        jPanel1.add(button1);
        jPanel2.add(button2);
        add(jPanel1);
        add(jPanel2);
        setLayout(new GridLayout(1,2));
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("one")){
            GrassField map = new GrassField(width,height,jungleRatio,startEnergy,plantEnergy,moveEnergy);
            SimulationEngine engine = new SimulationEngine(map,numberOfAnimals,numberOfPlants,delay);
            engine.run();
        }
        else if (e.getActionCommand().equals("two")){
            GrassField map1 = new GrassField(width,height,jungleRatio,startEnergy,plantEnergy,moveEnergy);
            SimulationEngine engine1 = new SimulationEngine(map1,numberOfAnimals,numberOfPlants,delay);
            engine1.run();
            GrassField map2 = new GrassField(width,height,jungleRatio,startEnergy,plantEnergy,moveEnergy);
            SimulationEngine engine2 = new SimulationEngine(map2,numberOfAnimals,numberOfPlants,delay);
            engine2.run();
        }
    }
}
