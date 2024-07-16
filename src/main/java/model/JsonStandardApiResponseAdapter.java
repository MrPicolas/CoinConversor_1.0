package model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonStandardApiResponseAdapter extends TypeAdapter<JsonStandardApiResponse> {

    @Override
    public void write(JsonWriter out, JsonStandardApiResponse value) throws IOException {
        out.beginObject();
        out.name("base_code").value(value.base_code());
        out.name("conversion_rates").beginObject();
        for (Map.Entry<String, Double> entry : value.conversion_rates().entrySet()) {
            out.name(entry.getKey()).value(entry.getValue());
        }
        out.endObject();
        out.endObject();
    }

    @Override
    public JsonStandardApiResponse read(JsonReader in) throws IOException {
        String baseCode = null;
        Map<String, Double> conversionRates = new HashMap<>();

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("base_code")) {
                baseCode = in.nextString();
            } else if (name.equals("conversion_rates")) {
                in.beginObject();
                while (in.hasNext()) {
                    String key = in.nextName();
                    Double value = in.nextDouble();
                    conversionRates.put(key, value);
                }
                in.endObject();
            } else {
                in.skipValue();
            }
        }
        in.endObject();

        return new JsonStandardApiResponse(baseCode, conversionRates);
    }
}
