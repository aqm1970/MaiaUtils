package com.maia_business_solutions;

import java.util.ArrayList;
import java.util.Comparator;

public class AlphaNumericComparator implements Comparator<String>
{
  private final boolean ignoreCase;
  
  public AlphaNumericComparator()
  {
    this(true);
  }
  
  public AlphaNumericComparator(final boolean ignoreCase)
  {
    this.ignoreCase = ignoreCase;
  }
  
  @Override
  public int compare(String string1, String string2)
  {
    int retVal = 0;
    
    final Object[] objects1 = getSplitObjects(string1);
    final Object[] objects2 = getSplitObjects(string2);
    
    for (int i = 0;
        retVal == 0 && (i < objects1.length || i < objects2.length);
        ++i)
    {
      final Object object1 = getObject(objects1, i);
      final Object object2 = getObject(objects2, i);
      
      if (object1 instanceof Long) {
        if (object2 instanceof Long)
          retVal = ((Long) object1).compareTo((Long) object2);
        else
          retVal = -1;
      }
      else if (object2 instanceof Long) {
        if (object1 != null)
          retVal = 1;
        else
          retVal = -1;
      }
      else {
        if (object1 == null)
          retVal = -1;
        else if (object2 == null)
          retVal = 1;
        else
          retVal = ((String)object1).compareTo((String)object2);
      }
      
      //System.out.println("Comparing: " + object1 + ", " + object2 + " = " + retVal);
    }
    
    return retVal;
  }
  
  private Object[] getSplitObjects(final String value)
  {
    Object[] splitString = new Object[0];
    
    ArrayList<Object> objects = new ArrayList<>();

    if (value != null) {
      if (value.isEmpty() == false) {
        for (int i = 0; i < value.length(); ) {
          if (Character.isDigit(value.charAt(i))) {
            final String number = parseNumericString(i, value);
            i += number.length();
            objects.add(Long.parseLong(number));
          }
          else {
            final String string = parseAlphaString(i, value);
            objects.add(string);
            i += string.length();
          }
        }

        splitString = objects.toArray();
      }
    }
    
    return splitString;
  }
  
  private static String parseNumericString(int idx, String value)
  {
    final StringBuilder builder = new StringBuilder();
    
    for (int i = idx;
        i < value.length() && Character.isDigit(value.charAt(i));
        ++i)
    {
      builder.append(value.charAt(i));
    }
    
    return builder.toString();
  }
  
  private String parseAlphaString(int idx, String value)
  {
    final StringBuilder builder = new StringBuilder();
    
    for (int i = idx;
        i < value.length() && Character.isDigit(value.charAt(i)) == false;
        ++i)
    {
      builder.append(value.charAt(i));
    }
    
    if (ignoreCase)
      return builder.toString().toLowerCase();
    else
      return builder.toString();
  }
  
  private static Object getObject(final Object[] array, final int idx)
  {
    Object retVal = null;
    
    if (idx < array.length)
      retVal = array[idx];
    
    return retVal;
  }
}