package adapters;

import beans.MenuItem;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MenuItemAdapter implements JsonSerializer<MenuItem>, JsonDeserializer<MenuItem>{

    private static final String CLASSNAME = "MenuItem";
    private static final String INSTANCE  = "INSTANCE";

    @Override
    public JsonElement serialize(MenuItem src, Type typeOfSrc,
            JsonSerializationContext context) {

        JsonObject retValue = new JsonObject();
        String className = src.getClass().getName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement elem = context.serialize(src); 
        retValue.add(INSTANCE, elem);
        return retValue;
    }

    @Override
    public MenuItem deserialize(JsonElement json, Type typeOfT,
    JsonDeserializationContext context) throws JsonParseException  {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();

        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
        return context.deserialize(jsonObject.get(INSTANCE), klass);
    }
}