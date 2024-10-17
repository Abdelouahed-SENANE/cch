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

        Set<Stage> stages = stageService.findAllStages();
        stages.forEach(c -> {
            System.out.println(c.getStartLocation() + "======>" + c.getEndLocation());
        });

    }


}