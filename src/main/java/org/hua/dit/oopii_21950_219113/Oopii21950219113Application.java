package org.hua.dit.oopii_21950_219113;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Oopii21950219113Application {


    public static void main(String[] args) {
        /**
         * With this line the spring container is being created. You can thing of it as the spring's JVM container.
         * Inside this container we can create spring Beans. Also in this container spring will create as many object
         * we marked with the annotation of @Component(same as @Service). Lastly because of the springs logic we do not
         * have to create the object anywhere, spring will instantiate one,and only one, object for us and return it.
         * The last is the singleton design pattern.
         *
         * @param Oopii21950219113Application.class
         *
         * @param args
         *        Command line arguments
         *
         * @return ConfigurableApplicationContext object
         *
         */
        SpringApplication.run(Oopii21950219113Application.class, args);

    }

}
