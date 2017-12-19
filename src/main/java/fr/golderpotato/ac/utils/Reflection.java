package fr.golderpotato.ac.utils;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Eliaz on 19/01/2017.
 */
public class Reflection {
    public static Class<?> getNmsClass(final String name) {
        final String className = "net.minecraft.server." + getVersion() + "." + name;
        return getClass(className);
    }

    public static Class<?> getCbClass(final String name) {
        final String className = "org.bungee.craftbukkit." + getVersion() + "." + name;
        return getClass(className);
    }

    public static Class<?> getUtilClass(final String name) {
        try {
            return Class.forName(name); //Try before 1.8 first
        } catch (ClassNotFoundException ex) {
            try {
                return Class.forName("net.minecraft.util." + name); //Not 1.8
            } catch (ClassNotFoundException ex2) {
                return null;
            }
        }
    }

    public static String getVersion() {
        final String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static Object getHandle(final Object wrapper) {
        final Method getHandle = makeMethod(wrapper.getClass(), "getHandle");
        return callMethod(getHandle, wrapper);
    }

    //Utils
    public static Method makeMethod(final Class<?> clazz, final String methodName, final Class<?>... paramaters) {
        try {
            return clazz.getDeclaredMethod(methodName, paramaters);
        } catch (NoSuchMethodException ex) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T callMethod(final Method method, final Object instance, final Object... paramaters) {
        if (method == null) throw new RuntimeException("No such method");
        method.setAccessible(true);
        try {
            return (T) method.invoke(instance, paramaters);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Constructor<T> makeConstructor(final Class<?> clazz, final Class<?>... paramaterTypes) {
        try {
            return (Constructor<T>) clazz.getConstructor(paramaterTypes);
        } catch (NoSuchMethodException ex) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T callConstructor(final Constructor<T> constructor, final Object... paramaters) {
        if (constructor == null) throw new RuntimeException("No such constructor");
        constructor.setAccessible(true);
        try {
            return (T) constructor.newInstance(paramaters);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Field makeField(final Class<?> clazz, final String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getField(final Field field, final Object instance) {
        if (field == null) throw new RuntimeException("No such field");
        field.setAccessible(true);
        try {
            return (T) field.get(instance);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void setField(final Field field, final Object instance, final Object value) {
        if (field == null) throw new RuntimeException("No such field");
        field.setAccessible(true);
        try {
            field.set(instance, value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Class<?> getClass(final String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

    public static <T> Class<? extends T> getClass(final String name, final Class<T> superClass) {
        try {
            return Class.forName(name).asSubclass(superClass);
        } catch (ClassCastException | ClassNotFoundException ex) {
            return null;
        }
    }

    // Fuzzy reflection

    public static Field getOnlyField(final Class<?> toGetFrom, final Class<?> type) {
        Field only = null;
        for (Field field : toGetFrom.getDeclaredFields()) {
            if (!type.isAssignableFrom(field.getClass())) continue;
            Preconditions.checkArgument(only == null, "More than one field of type %s on %s: %s and %s", type.getSimpleName(), toGetFrom.getSimpleName(), field.getName(), only.getName());
            only = field;
        }
        return only;
    }

    public static Method getOnlyMethod(final Class<?> toGetFrom, final Class<?> returnType, final Class<?>... paramSpec) {
        Method only = null;
        for (Method method : toGetFrom.getDeclaredMethods()) {
            if (!returnType.isAssignableFrom(method.getReturnType())) continue;
            if (!isParamsMatchSpec(method.getParameterTypes(), paramSpec)) continue;
            Preconditions.checkArgument(only == null, "More than one method matching spec on %s" + ((only.getName().equals(method.getName())) ? "" : ": " + only.getName() + " " + method.getName()), toGetFrom.getSimpleName());
            only = method;
        }
        return only;
    }

    public static boolean isParamsMatchSpec(final Class<?>[] parameters, final Class<?>... paramSpec) {
        if (parameters.length > paramSpec.length) return false;
        for (int i = 0; i < paramSpec.length; i++) {
            Class<?> spec = paramSpec[i];
            if (spec == null) continue;
            Class parameter = parameters[i];
            if (!spec.isAssignableFrom(parameter)) return false;
        }
        return true;
    }
}
