package ma.youcode.cch;



import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.CyclistService;
import ma.youcode.cch.service.interfaces.StageService;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = context.getBean(TeamService.class);
        CyclistService cyclistService = context.getBean(CyclistService.class);
        CompetitionService competitionService = context.getBean(CompetitionService.class);
        StageService stageService = context.getBean(StageService.class);

//        Team team = new Team();
//        team.setTeamName("C");
//        teamService.saveTeam(team);

//        Cyclist cyclist = new Cyclist();
//        cyclist.setFirstName("Mohammed");
//        cyclist.setLastName("RATID");
//        cyclist.setNationality("Z");
//        cyclist.setAge("25");
//        Team team = new Team();
//        team.setTeamId(UUID.fromString("182fbd00-ba75-47f3-953a-5d2125b60ce5"));
//        cyclist.setTeam(team);
//        cyclistService.saveCyclist(cyclist);

//        Stage stage = new Stage();
//        stage.setNumber(1);
//        stage.setStartDate(LocalDate.of(2024 , 9 , 22));
//        stage.setStartLocation("London");
//        stage.setEndLocation("Paris");
//        Competition competition = new Competition();
//        competition.setCompetitionId(UUID.fromString("e07e16d1-3668-4a1c-8ede-abe3b8b06e30"));
//        stage.setCompetition(competition);
//        stageService.saveStage(stage);

//        Competition competition = new Competition();
//        competition.setCompetitionName("Paris");
//        competition.setYear(Year.of(2026));
//        competition.setStartDate(LocalDateTime.of(2024 , 9 , 24 , 10 , 30));
//        competition.setEndDate(LocalDateTime.of(2024 , 10 , 24 , 10 , 30));
//        competitionService.saveCompetition(competition);

        List<Cyclist> cyclists = cyclistService.getSortedCyclists("teamName");
        cyclists.forEach(c -> {
            System.out.println(c.getLastName());
        });

    }


}