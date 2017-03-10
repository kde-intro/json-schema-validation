package ru.kde.intro.jsonsv;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.IOException;


/**
 * Created by Denis Krasilnikov (kde-intro)
 */

public class Main {

    public static String validate(String schemaPath, String responceFilePathOrString, String restype) throws IOException, ProcessingException {
        restype = restype.toLowerCase();
        JsonNode schemaFile = JsonLoader.fromPath(schemaPath);
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(schemaFile);
        JsonNode response;

        if (restype.equals("file")) {
            response = JsonLoader.fromPath(responceFilePathOrString);
        } else {
            if (restype.equals("string")) {
                response = JsonLoader.fromString(responceFilePathOrString);
            } else {

                return "ERROR: Validation failure. MESSAGE: Unknown type of the responce source. It should be 'file' or 'string'.";
            }
        }

        ProcessingReport report = schema.validate(response);

        return report.toString();

    }

}
