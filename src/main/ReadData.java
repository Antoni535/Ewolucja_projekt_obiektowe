import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadData {
    private JSONObject jsonObject;

    public ReadData(){
        try{
            String contents = new String(Files.readAllBytes(Paths.get("C:\\Users\\anton\\Desktop\\AntoniSzczepańskiProjekt1\\src\\main\\parameters.json")));
            this.jsonObject = new JSONObject(contents);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
