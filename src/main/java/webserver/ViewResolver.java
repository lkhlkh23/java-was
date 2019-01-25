package webserver;

import model.RequestEntity;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class ViewResolver {
    private static Map<RequestEntity, String> viewMappingResolver = new HashMap<>();

    private static final Logger logger = getLogger(ViewResolver.class);

    public static final String REDIRECT_KEYWORD = "redirect:";

    private static String root = "";

    static {
        try {
            root = new File(".").getCanonicalPath() + "/webapp";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String obtainFullPath(String path) {
        return String.format("%s/%s", root, path);
    }


    public static String obtainPath(String path) {
        if(path.contains(REDIRECT_KEYWORD)) {
            return obtainFullPath(obtainRemovePath(path));
        }

        return obtainFullPath(path);
    }

    public static String obtainRemovePath(String path) {
        return path.split(REDIRECT_KEYWORD)[1];
    }

    public static String obtainReturnView(RequestEntity requestEntity) {
        return viewMappingResolver.get(requestEntity);
    }
}
