package ru.job4j.di;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class Context {
    private Map<String, Object> els = new HashMap<String, Object>();

    public void reg(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        log.debug("Length constructor: {}", constructors.length);
        if (constructors.length > 1) {
            throw new IllegalStateException(
                    "Class has multiple constructors : " + cl.getCanonicalName());
        }
        Constructor con = constructors[0];
        log.debug(con.toString());
        List<Object> args = new ArrayList<Object>();
        for (Class arg : con.getParameterTypes()) {
            log.debug("Argument const: {}", arg.getCanonicalName());
            if (!els.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException(
                        "Object doesn't found in context : " + arg.getCanonicalName());
            }
            args.add(els.get(arg.getCanonicalName()));
        }
        try {
            els.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Coun't create an instance of : " + cl.getCanonicalName(), e);
        }
    }

    public <T> T get(Class<T> inst) {
        return (T) els.get(inst.getCanonicalName());
    }
}
