package com.romanidze.tennis_essay.processing;

import com.romanidze.tennis_essay.functions.FileWorkImplementation;
import com.romanidze.tennis_essay.functions.ParseImplementation;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Андрей Романов <steampart@gmail.com>
 */
public class LaunchFunction {
    
    public void launch(){
        
        Document doc = null;
        
        Properties p = new Properties();        
        
        try {
            
            Reader isr = new InputStreamReader(new FileInputStream("src\\main\\resources\\app.properties"));
            
            p.load(isr);
            
            doc = Jsoup.connect(p.getProperty("app.link")).get();
            
        } catch (IOException ex) {
            
            System.err.println("Ошибка ввода - вывода");
            
        }
        
        ParseImplementation parse = new ParseImplementation();
        
        String data = parse.getAllTitles(doc);
        
        FileWorkImplementation fileWork = new FileWorkImplementation();
        
        fileWork.saveData(data);
              
    }
    
}
