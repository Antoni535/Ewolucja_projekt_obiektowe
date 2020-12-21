public class Statistic {
    GrassField map;
    int year=0;
    public Statistic(GrassField map){
        this.map=map;
    }
    public int getActualNumberOfAnimals(){
        return map.listOfAnimals.size();
    }
    public int getActualNumberOfPlants(){
        return map.plants.size();
    }
    public void newYear(){
        this.year++;
    }
    public int getYear(){
        return this.year;
    }
    public float getAverageNumberOfChildren(){
        float tmp=0;
        for (Animal animal : map.listOfAnimals){
            tmp+=animal.children.size();
        }
        return  tmp/map.listOfAnimals.size();
    }
    public float getAverageEnergy(){
        float tmp=0;
        for (Animal animal : map.listOfAnimals){
            tmp+=animal.getEnergy();
        }
        return tmp/map.listOfAnimals.size();
    }
    public float getAverageLifeLength(){
        return map.averageLifeLength;
    }
}
