package com.comcast.century.ovt;


import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.testng.annotations.Test;

public class OvtTest {

	@Test
    public void getObjects() {

          Reflections reflections = new Reflections(
                           new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("com.comcast"))
                                         .filterInputsBy(new FilterBuilder().include("com\\.comcast\\..*\\.class"))
                                         .setScanners(new FieldAnnotationsScanner()));

          Set<Field> lists2 = reflections.getFieldsAnnotatedWith(org.openqa.selenium.support.FindBy.class);

          ArrayList<Field> arr = new ArrayList<Field>(lists2);
          
          Set<String> classes = new HashSet<String>();
          for (Field f : arr) {
                 classes.add(f.getDeclaringClass().getSimpleName());
          }
          try {
            	  	 
                     PrintStream printer = new PrintStream(new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"Results"+System.getProperty("file.separator")+"ObjectList.txt"));
                     printer.println("----------------------------------------");

                     Iterator<String> iterator = classes.iterator();
                     while (iterator.hasNext()) {
                           String classname = iterator.next().toString();
                           System.out.println(" ");
                           printer.println(classname);
                           printer.println(" ");
                           for (Field f : arr) {
                                  for (Annotation annotation : f.getAnnotations()) {
                                         Class<? extends Annotation> type = annotation.annotationType();                                         
                                         if (f.getDeclaringClass().getSimpleName().equals(classname)) {
                                                for (Method method : type.getDeclaredMethods()) {
                                                       Object value;

                                                       value = method.invoke(annotation, (Object[]) null);
                                                       if (!value.toString().isEmpty() && !method.getName().equals("how")) {
                                                              printer.println(f.getName() + ";" + method.getName() + "= " + value);
                                                       }
                                                }
                                         }
                                  }
                           }
                           printer.println("----------------------------------------");
                     }
                     printer.flush();
                     printer.close();
              } catch (IllegalAccessException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              } catch (IllegalArgumentException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              } catch (InvocationTargetException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
              }
       }

}
