package ma.youcode.cch;



import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.TeamService;
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
        CyclistService cyclistService = context.getBean(CyclistService.class);
        CompetitionService competitionService = context.getBean(CompetitionService.class);

        competitionService.test();
//        Cyclist cyclist = new Cyclist();
//        cyclist.setFirstName("Abdelouahed");
//        cyclist.setLastName("SENANE");
//        cyclist.setNationality("MOROCCO");
//        cyclist.setAge("30");
//        Team team = new Team();
//        team.setTeamId(UUID.fromString("4213ab41-13cc-4648-92fd-4295b4caf828"));
//        cyclist.setTeam(team);
//        cyclistService.saveCyclist(cyclist);
//
//        Set<Team> teams = teamService.findAllTeams();
//        teams.forEach(teamEl -> {
//            System.out.println(teamEl.getTeamName());
//            teamEl.getCyclists().forEach(c -> {
//                System.out.println(c.getFirstName());
//            });
//        });

    }


}