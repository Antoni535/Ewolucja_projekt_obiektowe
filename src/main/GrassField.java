import java.util.*;


public class GrassField implements IPositionChangeObserver{
    protected Map<Vector2d,List<Animal>> animals = new HashMap<>();
    protected List<Animal> listOfAnimals = new ArrayList<>();
    protected Map<Vector2d,Grass> plants = new HashMap<>();
    private final Vector2d size;
    private final Vector2d vec0 = new Vector2d(0,0);
    private final Vector2d jungleLowerLeft;
    private final Vector2d jungleUpperRight;
    public final int jungleRatio;
    public final int startEnergy;
    public final int plantEnergy;
    public final int moveEnergy;
    public final int needEnergyToMultiply;
    Random generator= new Random();

    public GrassField(int width,int height,int jungleRatio,int startEnergy,int plantEnergy,int moveEnergy){
        this.size =new Vector2d(width-1,height-1);
        this.jungleLowerLeft=new Vector2d(0,0);
        this.jungleUpperRight=new Vector2d(width/jungleRatio-1,height/jungleRatio-1);
        this.jungleRatio=jungleRatio;
        this.startEnergy=startEnergy;
        this.plantEnergy=plantEnergy;
        this.moveEnergy=moveEnergy;
        this.needEnergyToMultiply=startEnergy/2;
    }
    public Vector2d lowerLeftVector() {
        return vec0;
    }
    public Vector2d upperRightVector() {
        return size;
    }

    public void firstAddAnimal(int n){
        for(int i=0;i<n;i++) {
            Vector2d position= new Vector2d(generator.nextInt(size.x+1), generator.nextInt(size.y+1));
            while (animals.containsKey(position)) {
                position = new Vector2d(generator.nextInt(size.x + 1), generator.nextInt(size.y + 1));
            }
            Animal animal=new Animal(this,position,startEnergy);
            place(animal);
        }
    }
    public void addAnimal(Animal animal){
        if (this.animals.containsKey(animal.getPosition())){
            this.animals.get(animal.getPosition()).add(animal);
            this.animals.get(animal.getPosition()).sort(new Comparator<Animal>() {
                @Override
                public int compare(Animal o1, Animal o2) {
                    if (o1.getEnergy()>o2.getEnergy()) return -1;
                    else return 1;
                }
            });
        }
        else{
            List<Animal> anim=new ArrayList<>();
            anim.add(animal);
            animals.put(animal.getPosition(),anim);
        }
        if (!listOfAnimals.contains(animal)) this.listOfAnimals.add(animal);
    }

    public void firstAddPlants(int n) {
        for (int i=0;i<n/2;i++) {
            addPlants();
        }
    }
    public void addPlants(){
        int rangeX = this.size.x;
        int rangeY = this.size.y;
        int jungleRangeX = this.jungleUpperRight.x;
        int jungleRangeY = this.jungleUpperRight.y;
        int maxCount=5*rangeX*rangeY;
        int jungleMaxCount=5*jungleRangeX*jungleRangeY;
        int i=0;
        int j=0;

        Grass jungleGrass= new Grass(new Vector2d(generator.nextInt(jungleRangeX+1),generator.nextInt(jungleRangeY+1)));
        while (isOccupied(jungleGrass.getPosition()) && i<jungleMaxCount) {
            jungleGrass= new Grass(new Vector2d(generator.nextInt(jungleRangeX+1),generator.nextInt(jungleRangeY+1)));
            i++;
        }
        if (i<jungleMaxCount) {
            this.plants.put(jungleGrass.getPosition(), jungleGrass);
        }
        Grass grass= new Grass(new Vector2d(generator.nextInt(rangeX+1),generator.nextInt(rangeY+1)));
        while (isOccupied(grass.getPosition()) || (grass.getPosition().x<jungleRangeX && grass.getPosition().y<jungleRangeY) && j<maxCount){
            grass= new Grass(new Vector2d(generator.nextInt(rangeX+1),generator.nextInt(rangeY+1)));
            j++;
        }
        if (j<maxCount) {
            this.plants.put(grass.getPosition(), grass);
        }
    }

    public boolean canPlace(Animal animal){
        if (this.animals.containsKey(animal.getPosition())){
            return false;
        }
        else return true;
    }
    public boolean place(Animal animal) {
        if (canPlace(animal)) {
            addAnimal(animal);
            animal.addObserver(this);
            return true;
        }
        else return false;
    }
    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position) || this.plants.containsKey(position);
    }
    public Object objectAt(Vector2d position) {
        if (isOccupied(position)){
            if (this.animals.containsKey(position)){
                return this.animals.get(position).get(0);
            }
            else{
                return this.plants.get(position);
            }
        }
        return null;
    }

    public Animal multiplication(Animal animal1,Animal animal2){
        int energy=(animal1.getEnergy()/4)+ (animal2.getEnergy()/4);
        animal1.changeEnergy(-animal1.getEnergy()/4);
        animal2.changeEnergy(-animal2.getEnergy()/4);
        int n= generator.nextInt(8);
        MapDirection orientation=animal1.getOrientation();
        for (int i=0;i<n;i++){
            orientation=orientation.next();
        }
        Vector2d tmp=animal1.getPosition().add(orientation.toUnitVector());
        int j=0;
        while (isOccupied(tmp) && j<8){
            orientation=orientation.next();
            tmp=animal1.getPosition().add(orientation.toUnitVector());
            j++;
        }
        Vector2d babyPositon=tmp;
        Gen gen=new Gen(animal1.getGenList(),animal2.getGenList());
        Animal baby=new Animal(this,babyPositon,energy,gen);
        baby.addObserver(this);
        animal1.children.add(baby);
        animal2.children.add(baby);
        return baby;
    }
    public void multiply(){
        List<Animal> babyList=new ArrayList<>();
        for (List<Animal> animalList : animals.values()){
            if (animalList.size()>1){
                if (animalList.get(0).getEnergy()>=this.needEnergyToMultiply && animalList.get(1).getEnergy()>=this.needEnergyToMultiply){
                    babyList.add(multiplication(animalList.get(0),animalList.get(1)));
                }
            }
        }
        for (Animal baby : babyList){
            addAnimal(baby);
        }
    }

    public void eat(){
        List<Grass> grassList=new ArrayList<>();
        for (Grass grass : plants.values()){
            if (animals.containsKey(grass.getPosition())){
                List<Animal> animalList= animals.get(grass.getPosition());
                int i=0;
                int maxEnergy=animalList.get(0).getEnergy();
                for (Animal animal : animalList){
                    if(animal.getEnergy()==maxEnergy) i++;
                    else break;
                }
                for (Animal animal : animalList){
                    if(animal.getEnergy()==maxEnergy){
                        animal.changeEnergy(plantEnergy/i);
                    }
                    else break;
                }
                grassList.add(grass);
            }
        }
        for (Grass grass : grassList){
            plants.remove(grass.getPosition());
        }
    }

    public void deleteDeadAnimals(){
        List<Animal> deleteAnimalList= new ArrayList<>();
        for (List<Animal> animalList : animals.values()){
            for (Animal animal : animalList){
                if (animal.getEnergy()<1){
                    deleteAnimalList.add(animal);
                }
            }
        }
        List<Animal> animalsList;
        for (Animal animal : deleteAnimalList){
            animalsList=animals.get(animal.getPosition());
            if (animalsList.size()>1) animals.get(animal.getPosition()).remove(animal);
            else animals.remove(animal.getPosition());
        }
        for (Animal animal : deleteAnimalList){
            listOfAnimals.remove(animal);
        }
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition,Animal animal){
        if (this.animals.get(oldPosition).size()>1)  this.animals.get(oldPosition).remove(animal);
        else this.animals.remove(oldPosition);
        this.addAnimal(animal);
    }
    public List<Animal> getListOfAnimals(){
        return this.listOfAnimals;
    }
}
