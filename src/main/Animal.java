import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private GrassField map;
    private int energy;
    public Gen gen;
    public List<IPositionChangeObserver> observerList = new ArrayList<>();
    public int age;
    public List<Animal> children;
    Random generator= new Random();

    public Animal(GrassField map, Vector2d initialPosition, int energy){
        this.map=map;
        this.position=initialPosition;
        this.energy=energy;
        this.age=0;
        int i=generator.nextInt(8);
        this.orientation=randomOrientation(i);
        this.gen=new Gen();
        this.children=new ArrayList<>();
    }
    public Animal(GrassField map, Vector2d initialPosition, int energy,Gen gen){
        this.map=map;
        this.position=initialPosition;
        this.energy=energy;
        this.age=0;
        int i=generator.nextInt(8);
        this.orientation=randomOrientation(i);
        this.gen=gen;
        this.children=new ArrayList<>();
    }
    public MapDirection randomOrientation(int n){
        switch(n){
            case 0: return MapDirection.NORTH;
            case 1: return MapDirection.NORTH_EAST;
            case 2: return MapDirection.EAST;
            case 3: return MapDirection.SOUTH_EAST;
            case 4: return MapDirection.SOUTH;
            case 5: return MapDirection.SOUTH_WEST;
            case 6: return MapDirection.WEST;
            case 7: return MapDirection.NORTH_WEST;
            default: return null;
        }
    }
    @Override
    public String toString() {
        switch(orientation){
            case NORTH:  return "N";
            case WEST: return "W";
            case SOUTH: return "S";
            case EAST: return "E";
            case NORTH_WEST: return "NW";
            case SOUTH_WEST: return "SW";
            case NORTH_EAST: return "NE";
            case SOUTH_EAST: return "SE";
            default: return null;
        }
    }

    public void move(int n){
        for (int i=0;i<n;i++){
            this.orientation=this.orientation.next();
        }
        Vector2d oldPosition=this.position;
        Vector2d tmp=this.position.add(this.orientation.toUnitVector());
        this.position=new Vector2d((tmp.x+map.upperRightVector().x+1)%(map.upperRightVector().x+1),(tmp.y+map.upperRightVector().y+1)%(map.upperRightVector().y+1));
        this.positionChanged(oldPosition,this.position);
    }

    public Vector2d getPosition(){
        return position;
    }
    public MapDirection getOrientation(){
        return this.orientation;
    }
    public int getEnergy(){return this.energy;}
    public void changeEnergy(int n){this.energy=this.energy+n;}
    public int[] getGenList() {return this.gen.genes;}
    public void addObserver(IPositionChangeObserver observer){
        observerList.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observerList.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver i : observerList){
            i.positionChanged(oldPosition,newPosition,this);
        }
    }
}