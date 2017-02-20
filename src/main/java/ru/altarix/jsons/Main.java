package ru.altarix.jsons;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.IOException;

/*
* Comments should here and below
*/

public class Main {
    
    public static String validate(String schemaPath, String responceFilePathOrString, String restype) throws IOException, ProcessingException {
        restype = restype.toLowerCase();
        final JsonNode schemafile = JsonLoader.fromPath(schemaPath);
        JsonNode responce;

        if (restype.equals("file")) {
            responce = JsonLoader.fromPath(responceFilePathOrString);
        } else {
            if (restype.equals("string")) {
                responce = JsonLoader.fromString(responceFilePathOrString);
            } else {
                return "ERROR: Validation failure. MESSAGE: Unknown type of responce resource. It should be 'file' or 'string'.";
            }
        }

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = factory.getJsonSchema(schemafile);

        ProcessingReport report;

        report = schema.validate(responce);
        return report.toString();
    }

}