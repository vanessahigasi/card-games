package tech.bts.cardgames.util;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.IOException;

public class HandlebarsUtil {

    private static Handlebars handlebars;

    static { //code only executed once

        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".hbs");
        handlebars = new Handlebars(loader);
    }

    public static Template compile(String templateName) throws IOException {

        return  handlebars.compile(templateName);
    }
}
