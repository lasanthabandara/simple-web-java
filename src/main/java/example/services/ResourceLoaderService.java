package example.services;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//@ComponentScan("resourceLoaderService")
public class ResourceLoaderService implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String loadResourceDataByClassPath(String path) throws IOException{
        Resource resource = resourceLoader.getResource(path);
        InputStream in = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String data = "";
        while (true){
            String line = reader.readLine();
            if(line==null) break;
            data+=line+"<br/>";
        }
        return data;
    }
}
