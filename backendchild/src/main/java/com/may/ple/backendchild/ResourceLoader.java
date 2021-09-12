package com.may.ple.backendchild;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.may.ple.backendchild.action.MyMessage;


public class ResourceLoader extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(MyMessage.class);
        return classes;
    }

}