package fr.golderpotato.ac.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Eliaz on 15/01/2017.
 * Credit to Faiden
 */
public abstract class ReflectionUtils
{
    public static Object invoke(Class<?> clazz, String methodname, Class<?>[] methodparamaters, Object instance, Object[] arguments)
    {
        try
        {
            Method m = clazz.getDeclaredMethod(methodname, methodparamaters);
            m.setAccessible(true);
            return m.invoke(instance, arguments);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void setValue(Object instance, String fieldName, Object value)
            throws Exception
    {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... paramatertypes)
            throws NoSuchMethodException, SecurityException
    {
        Method m = clazz.getDeclaredMethod(name, paramatertypes);
        m.setAccessible(true);
        return m;
    }

    public static Object getFieldObject(Class<?> clazz, String name, Object instance)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        Field f = clazz.getDeclaredField(name);
        f.setAccessible(true);
        return f.get(instance);
    }

    public static Field getField(Class<?> clazz, String name)
            throws NoSuchFieldException, SecurityException
    {
        Field f = clazz.getDeclaredField(name);
        f.setAccessible(true);
        return f;
    }
}