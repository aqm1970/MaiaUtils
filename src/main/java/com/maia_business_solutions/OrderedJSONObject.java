package com.maia_business_solutions;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderedJSONObject extends JSONObject
{
  private static final Logger LOGGER = Logger.getAnonymousLogger();

  public OrderedJSONObject()
  {
    super();

    try {
      final Field mapField = JSONObject.class.getDeclaredField("map");

      mapField.setAccessible(true);
      mapField.set(this, new LinkedHashMap<String, Object>());
      mapField.setAccessible(false);
    }
    catch (RuntimeException | ReflectiveOperationException e) {
      LOGGER.info("Unable to use LinkedMap ... using default HashMap");
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public static OrderedJSONObject copy(final JSONObject object,
      final String ... fields)
  {
    final OrderedJSONObject retVal = new OrderedJSONObject();
    
    for (int i = 0; i < fields.length; ++i) {
      try {
        retVal.put(fields[i], object.get(fields[i]));
      }
      catch (JSONException e) {
        e.printStackTrace();
      }
    }

    return retVal;
  }
}