/*
 *     SPC-CS-DB
 *     Copyright (C) 2020  Colin Chow
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.colin.utils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.*;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * Some utilities involving reflection.
 * @author Colin
 */
public class ReflectUtils {
    private ReflectUtils(){
        throw new AssertionError();
    }

    /**
     * Gets the no-argument constructor of a class, even if it is non-public.
     * @param <T> The type of the class
     * @param cls The class to get a constructor for
     * @return An Optional containing either the constructor, or is empty if no constructor can be found.
     */
    public static <T> Optional<Constructor<T>> getNoArgConstructor(Class<T> cls){
        Constructor<T> cons;
        try {
            cons = cls.getDeclaredConstructor();
            cons.setAccessible(true);
        } catch (NoSuchMethodException ex) {
            //additional logging?
            System.out.println("lint");
            return Optional.empty();
        } catch (SecurityException ex) {
            return Optional.empty();
        }
        return Optional.of(cons);
    }

    /**
     * Returns a list of loaded classes from the given directory containing JAR files, with the specified superclass.
     * @param <T> The type of the superclass
     * @param p The directory to look for JAR files
     * @param superclass The superclass of classes to load
     * @return A set containing all the classes loaded
     * @throws MalformedURLException If the given path is malformed
     * @throws IOException If an I/O exception occurs
     * @throws ClassNotFoundException If the superclass is not found
     */
    public static <T> Set<Class<T>> loadSubclasses(Path p,Class<T> superclass) throws MalformedURLException, IOException, ClassNotFoundException{
        URLClassLoader url = URLClassLoader.newInstance(Files.walk(p).map(pth -> {
            try {
                return pth.toUri().toURL();
            } catch (MalformedURLException ex) {
                throw new IllegalArgumentException("Given path " + pth + " is malformed.");
            }
        }).toArray(URL[]::new));
        Set<Class<T>> noDup = new HashSet<>();
        for(Path ps : getJars(p)){
            List<String> toCheck = classNamesInJar(ps);
            for(String s : toCheck){
                if(s.contains("module-info") || s.contains("org.apache.logging")){
                    continue;
                }
                Class<?> cs = url.loadClass(s);
                if(superclass.isAssignableFrom(cs)){
                    @SuppressWarnings("unchecked")
                    //isAssignableFrom() guards that it is a subclass of T
                    Class<T> cls = (Class<T>) cs;
                    noDup.add(cls);
                }
            }
        }
       return noDup;
    }

    /**
     * Returns a list of concrete loaded subclasses from the given directors containing JAR files, with the specified superclass.
     * @param <T> The supertype to load; the type of the superclass
     * @param p The path to load classes from
     * @param superclass The superclass to filter loaded classes
     * @return A list containing all the subclasses loaded
     * @throws MalformedURLException When the given path is malformed
     * @throws IOException If an I/O error occurs
     * @throws ClassNotFoundException If the superclass is not found
     */
    public static <T> List<Class<T>> loadConcreteSubclasses(Path p,Class<T> superclass) throws MalformedURLException, IOException, ClassNotFoundException{
        return loadSubclasses(p,superclass).stream().filter(cls -> !cls.isInterface())
                .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                .filter(cls -> !cls.isSynthetic())
                .filter(cls -> !cls.isAnonymousClass())
                .collect(Collectors.toList());
    }
    
    /**
     * Provides a list of concrete subclasses to the given callback function.
     * @param p The path to load classes from
     * @param superclass The superclass to filter loaded classes
     * @param callback The callback to pass the loaded classes to
     * @param <T> The supertype to load; the type of the superclass
     * @throws IOException If an I/O error occurs
     * @throws ClassNotFoundException If the superclass is not found
     */
    public static <T> void loadConcreteCallback(Path p, Class<T> superclass, Consumer<List<Class<T>>> callback) throws IOException, ClassNotFoundException {
        callback.accept(loadConcreteSubclasses(p,superclass));
    }

    //Helpers
    private static List<Path> getJars(Path p) throws IOException{
         PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:**.jar");
        return Files.walk(p).filter(pm::matches).collect(Collectors.toList());
    }
    private static List<String> classNamesInJar(Path p) throws IOException{
        //Fragile due to replacing class strings
        JarFile jf = new JarFile(p.toFile());
        List<String> results = new ArrayList<>();
        Enumeration<JarEntry> entries = jf.entries();
        while(entries.hasMoreElements()){
            JarEntry je = entries.nextElement();
            if(je.getName().endsWith(".class")){
                String resolver = je.getName().substring(0,je.getName().lastIndexOf(".class"));
                resolver = resolver.replaceAll("\\\\",".");
                resolver = resolver.replaceAll("/", ".");
                results.add(resolver);
            }
        }
        return results;
    }
}
