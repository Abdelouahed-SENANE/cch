package ma.youcode.cch;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.service.interfaces.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cyclist cyclist = new Cyclist();
        cyclist.setAge("29");
        cyclist.setFirstName("Abdelouahed"); // This should trigger a validation error
        cyclist.setLastName("SENANE");
        cyclist.setNationality("Morocco");
        CyclistService cyclistService = context.getBean(CyclistService.class);
        Validator validator = context.getBean(Validator.class);
        // Validate the cyclist object
        Set<ConstraintViolation<Cyclist>> violations = validator.validate(cyclist);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Cyclist> violation : violations) {
                System.out.println(violation.getMessage());
            }
        } else {
            Cyclist c = cyclistService.save(cyclist);
            System.out.println(c.toString() + "Cyclist saved successfully!");
        }


    }


}