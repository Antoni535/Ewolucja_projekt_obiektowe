import java.util.Arrays;
import java.util.Random;

public class Gen {
    Random generator= new Random();
    public int[] genes=new int[32];
    private int[] genesCounter=new int [8];
    public Gen() {
        for (int i = 0; i < 32; i++) {
            int gen = generator.nextInt(8);
            genes[i] = gen;
            genesCounter[gen]++;
        }
        for(int i=0;i<8;i++){
            if (genesCounter[i]==0){
                int tmp = generator.nextInt(8);
                while(genesCounter[tmp]<2){
                    tmp = generator.nextInt(8);
                }
                genesCounter[tmp]--;
                genesCounter[i]++;
                for (int j=0;j<32;j++){
                    if (genes[j]==tmp){
                        genes[j]=i;
                        break;
                    }
                }
            }
        }
        Arrays.sort(genes);
    }
    public Gen(int[] genes1,int[] genes2){
        int idx1= 1+generator.nextInt(31);
        int idx2= 1+generator.nextInt(31);
        while (idx1==idx2){
            idx2=1+generator.nextInt(31);
        }
        if(idx1>idx2){
            int tmp=idx1;
            idx1=idx2;
            idx2=tmp;
        }
        int part1= generator.nextInt(2);
        int part2= generator.nextInt(2);
        int part3;
        if (part1==part2 && part1==0) part3=1;
        else if(part1==part2 && part2==1) part3=0;
        else part3= generator.nextInt(2);
        for (int i=0;i<idx1;i++){
            if(part1==0){
                genes[i]=genes1[i];
                genesCounter[genes1[i]]++;
            }
            else{
                genes[i]=genes2[i];
                genesCounter[genes2[i]]++;
            }
        }
        for (int i=idx1;i<idx2;i++){
            if(part2==0){
                genes[i]=genes1[i];
                genesCounter[genes1[i]]++;
            }
            else{
                genes[i]=genes2[i];
                genesCounter[genes2[i]]++;
            }
        }
        for (int i=idx2;i<32;i++){
            if(part3==0){
                genes[i]=genes1[i];
                genesCounter[genes1[i]]++;
            }
            else{
                genes[i]=genes2[i];
                genesCounter[genes2[i]]++;
            }
        }
        for(int i=0;i<8;i++){
            if (genesCounter[i]==0){
                int tmp = generator.nextInt(8);
                while(genesCounter[tmp]<2){
                    tmp = generator.nextInt(8);
                }
                genesCounter[tmp]--;
                genesCounter[i]++;
                for (int j=0;j<32;j++){
                    if (genes[j]==tmp){
                        genes[j]=i;
                        break;
                    }
                }
            }
        }
        Arrays.sort(genes);
    }
}
