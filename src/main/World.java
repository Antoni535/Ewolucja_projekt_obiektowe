import org.json.JSONObject;
import java.lang.*;

public class World {
    public static void main(String[] args) {
        ReadData readData=new ReadData();
        JSONObject jsonObject= readData.getJsonObject();
        StartSimulation startSimulation=new StartSimulation(jsonObject);
    }
}

