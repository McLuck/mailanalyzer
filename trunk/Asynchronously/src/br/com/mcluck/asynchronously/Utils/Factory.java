package br.com.mcluck.asynchronously.Utils;

import br.com.mcluck.asynchronously.annotation.Asynchronous;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 *
 * @author Lucas Israel
 */
public class Factory {
    /**
     * Create object that supports asynchronous methods using the annotation @Asynchonous
     * @param realObject A instance of the object
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public synchronized static Object getInstance(Object realObject) throws InstantiationException, IllegalAccessException{
        return getInstance(realObject.getClass());
    }
    /**
     * Create object that supports asynchronous methods using the annotation @Asynchonous
     * @param realClass A Class of the object
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public synchronized static Object getInstance(Class realClass) throws InstantiationException, IllegalAccessException{
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(realClass);
        MethodHandler mi = new MethodHandler() {
            public Object invoke(final Object o, Method method, final Method method1, final Object[] os) throws Throwable {
                if(method.isAnnotationPresent(Asynchronous.class)){
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                method1.invoke(o, os);
                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalArgumentException ex) {
                                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvocationTargetException ex) {
                                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }).start();
                    return null;
                }else{
                    return method1.invoke(o, os);
                }
            }
        };

        f.setFilter(new MethodFilter() {
            public boolean isHandled(Method method) {
                return !method.getName().equals("finalize");
            }
        });

        Class c = f.createClass();
        Object o = ((ProxyObject)c.newInstance());
        ((ProxyObject)o).setHandler(mi);
        return o;
    }
}
