package utility;

import Command.ExecuteScript;
import Except.Check;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Script {
    String s;
    File file;
    List<String> lines;
    boolean hlt = false;
    public Script(String s, List <String> lines, File file){
        this.file=file;
        this.s=s;
        this.lines=lines;
    }
    public void execute(){
        ExecuteScript execute_script = new ExecuteScript(s);
        execute_script.Start();
        FileWriter writer = null;
        try {
            writer = new FileWriter(file,false);
            for(String s : lines){
                if(s.equals("exit")){
                    hlt=true;
                }
                writer.write(s+"\n");
            }
            writer.flush();
            writer.close();
            Check.CheckDellScript();
            if(hlt){
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
