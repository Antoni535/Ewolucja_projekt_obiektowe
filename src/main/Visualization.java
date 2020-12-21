import javax.swing.*;

public class Visualization {
    private GrassField map;
    private SimulationEngine engine;
    private MapVisualizer mapVisualizer;
    private JFrame jFrame;
    private JMenuBar buttons;
    private JMenuItem stopItem;
    private JMenuItem startItem;
    private Statistic statistic;
    private PrintStatistic plotAndStatistic;

    public Visualization(GrassField map, SimulationEngine engine,MapVisualizer mapVisualizer,Statistic statistic){
        this.map=map;
        this.engine=engine;
        this.jFrame=new JFrame("Wizualizacja");
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setVisible(true);
        this.mapVisualizer=mapVisualizer;
        this.jFrame.setSize(1400,800);
        this.jFrame.add(this.mapVisualizer);
        this.statistic=statistic;
        this.plotAndStatistic=new PrintStatistic(statistic);
        startItem = new JMenuItem("START");
        stopItem= new JMenuItem("STOP");
        startItem.setActionCommand("START");
        stopItem.setActionCommand("STOP");
        startItem.addActionListener(engine);
        stopItem.addActionListener(engine);
        buttons=new JMenuBar();
        buttons.add(startItem);
        buttons.add(stopItem);
        this.jFrame.setJMenuBar(buttons);
        this.mapVisualizer.setSize(1,1);
        plotAndStatistic.setSize(1,1);
        this.jFrame.add(plotAndStatistic);

    }
    public void update(){
        mapVisualizer.repaint();
        plotAndStatistic.repaint();
    }
    
    
    
}
