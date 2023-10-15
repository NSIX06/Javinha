package br.com.felipebugalho.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void copyNoNullProperties(Object source, Object target){

       BeansUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
    
    public static String[] getNullPropertyNames(Object source ){
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds){

        Object srcValue = src.getPropertyValue(pd.getName());

        if(srcValue == null)
        {

            emptyNames.add(pd.getName());
        }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
