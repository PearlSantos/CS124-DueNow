package DueNow.decoratorfactory;

/**
 * Created by IanDeLaCruz on 20/05/2016.
 */
public class FactoryProducer {
    public static AbstractTaskFactory getFactory(String factoryType){
        switch (factoryType) {
            case "Personal":
                return new PersonalTaskFactory();
            case "School":
                return new SchoolTaskFactory();
        }
    }
}
