package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FreeMarkerTests {

    @Test
    public void testFreeMarker() throws IOException, TemplateException {
        // Create a FreeMarker configuration object
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        // Specify the source where the templates are loaded from
        // Here, we're assuming the 'templates' folder is in the classpath
        cfg.setClassForTemplateLoading(FreeMarkerTests.class, "/templates");

        // Set how errors will appear.
        // During web page design you usually want to see HTML that describes the error well.
        // In production you would often like to hide details from the user.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        // Specify the charset that the template uses for output
        cfg.setDefaultEncoding("UTF-8");

        // Get the template
        Template template = cfg.getTemplate("greeting.ftl");

        // Create the data model (variables to be passed to the template)
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("user", "John Doe");

        List<String> favoriteColors = new ArrayList<>();
        favoriteColors.add("blue");
        favoriteColors.add("green");
        favoriteColors.add("red");
        dataModel.put("colors", favoriteColors);

        dataModel.put("isAdmin", true);

        // Process the template with the data model
        StringWriter out = new StringWriter();
        template.process(dataModel, out);
        String output = out.toString();

        assertThat(output).contains("Hello, John Doe!");
    }
}
