package ro.teamnet.zerotohero.reflection;

import ro.teamnet.zerotohero.reflection.demoobjects.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * TO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {

    private static int varsta;

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //TOD get the class for a String object, and print it
        String todo1 = new String("Nicusor");
        System.out.println(todo1.getClass());

        //TO get the class of an Enum, and print it

        Class todo2 = Enumclass.DINAMO.getClass();

        System.out.println(todo2);


        //TO get the class of a collection, and print it
        List<String> listTodo3 = new ArrayList<>();
        Class todo3 = listTodo3.getClass();
        System.out.println(todo3);

        //TOD get the class of a primitive type, and print it

        System.out.println(int.class);

        //TO get and print the class for a field of primitive type
        Todo5 todo5 = new Todo5();
        Class class5 = todo5.getClass();
        Field field = class5.getDeclaredField("varsta");
        System.out.println(field.getType());

        //TO get and print the class for a primitive type, using the wrapper class


        Class c = Integer.TYPE;
        System.out.println(c);


        //TO get the class for a specified class name
        Class todo7 = Class.forName("ro.teamnet.zerotohero.reflection.demoobjects.Todo5");
        System.out.println(todo7);

        //TO get the superclass of a class, and print it
        Todo8 todo8 = new Todo8();
        Class t8 = todo8.getClass();
        System.out.println("get the superclass || " + t8.getSuperclass());

        //TOD get the superclass of the superclass above, and print it
        Todo9 todo9 = new Todo9();
        System.out.println(todo9.getClass().getSuperclass().getSuperclass());

        //TO get and print the declared classes within some other class
        System.out.println("\n Afisam clasele interioare ale unei clase");
        Class[] childrens = todo9.getClass().getDeclaredClasses();
        for (int i = 0; i < childrens.length; i++)
            System.out.println(childrens[i].getName());


        //TOD print the number of constructors of a class
        Constructore constructore = new Constructore(4);
        Class constructor= constructore.getClass();
        Constructor[] constr = constructor.getDeclaredConstructors();
        System.out.println("\nAfisam constructorii unei clase: ");
        for(int i=0;i<constr.length;i++)
         System.out.println(constr[i]);


        //TO get and invoke a public constructor of a class
        System.out.println("\nConstructorul ales este: ");
        System.out.println(constructor.getConstructor(int.class,int.class).newInstance(3,6));


        //TOD get and print the class of one private field
        System.out.println("\nPrivate field: ");

        Field insideClassField = InsideClass.class.getDeclaredField("inside");
        System.out.println(insideClassField.getType());

        //TO set and print the value of one private field for an object

        System.out.println("\nPrivate field for object of a class: ");
        InsideClass insideClass= new InsideClass();
        Field fieldForObject = insideClass.getClass().getDeclaredField("inside");
        fieldForObject.setAccessible(true);
        fieldForObject.set(insideClass,"ana");
        System.out.println(fieldForObject.get(insideClass));

        //TO get and print only the public fields class
        System.out.println("\nJust the private fields of this class: ");
        Field[] publicFields = insideClass.getClass().getFields();
        for(int i =0;i<publicFields.length;i++)
            System.out.println(publicFields[i].get(insideClass));


        //TO get and invoke one public method of a class
        System.out.println("\nO metoda publica selectata de mine este : ");
        Method publicMethod = insideClass.getClass().getMethod("telefon");
        publicMethod.invoke(insideClass);

        //TO get and invoke one inherited method of a class
        System.out.println("\nO metoda publica selectata de mine din super este : ");
        Method superClassMethod = insideClass.getClass().getMethod("telefonSuper");
        superClassMethod.invoke(insideClass);

        //TOD invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        System.out.println("\n Apelam clasic  metodele");
        System.out.println(System.currentTimeMillis());
        for(int i =0 ;i<1000;i++){
            insideClass.celular();
        }
        System.out.println(System.currentTimeMillis());

        //TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        for(int i=0;i<1000;i++){
            insideClass.getClass().getMethod("celular").invoke(insideClass);
        }
        System.out.println(System.currentTimeMillis());
        //what do you observe?

    }
}
