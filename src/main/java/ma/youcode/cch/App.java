package ma.youcode.cch;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = context.getBean(TeamService.class);

        Team team = new Team();
        team.setTeamName("new Team D");
        Set<Team> teams = teamService.findAllTeams();

        System.out.println(teams.toString());

    }


}