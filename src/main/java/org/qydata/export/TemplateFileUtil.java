package org.qydata.export;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by jonhn on 2017/3/14.
 */
public class TemplateFileUtil {

    public static FileInputStream getTemplates(String tempName) throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile("classpath:excel-templates/"+tempName));
    }

}
