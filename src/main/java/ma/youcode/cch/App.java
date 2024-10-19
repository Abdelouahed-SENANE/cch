package ma.youcode.cch;


import jakarta.validation.constraints.Past;
import ma.youcode.cch.entity.*;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.service.interfaces.*;
import ma.youcode.cch.utils.DateTimeParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
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
        GeneralResultService generalResultService = context.getBean(GeneralResultService.class);
        ResultService resultService = context.getBean(ResultService.class);


//        UUID cyclistId = UUID.fromString("da346d6b-c80e-4701-88ce-7943bbbe284a");
//        UUID competitionId = UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b");
//
//        try {
//            resultService.saveResult(cyclistId, competitionId);
//            System.out.println("This cyclist can added on this stage ");
//        }catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }

//        Competition competition = new Competition();
//        competition.setCompetitionId(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));
//        Cyclist cyclist = new Cyclist();
//        cyclist.setCyclistId(UUID.fromString("da346d6b-c80e-4701-88ce-7943bbbe284a"));
////
//        GeneralResultId generalResultId = new GeneralResultId();
//        generalResultId.setCompetitionId(competition.getCompetitionId());
//        generalResultId.setCyclistId(cyclist.getCyclistId());
//        GeneralResult generalResult = new GeneralResult();
//        generalResult.setGeneralResultId(generalResultId);
//        generalResult.setCompetition(competition);
//        generalResult.setCyclist(cyclist);
//        generalResult.setGeneralRank(0);
//        generalResult.setDuration(Duration.ofMinutes(100));
//        generalResultService.saveGeneralResult(generalResult);


//        Set<Competition>  competitions = new HashSet<>();
//        String stringDate = "2025-09-10";
//        String place = "Casablanca";
//        LocalDate parsedDate = DateTimeParser.parseDate(stringDate);
//        if (parsedDate != null) {
//        competitions = competitionService.getFilteredCompetitions(place ,parsedDate);
//        }

//        competitionService.getFilteredCompetitions()

//        Ajouter Team
//        Team team = new Team();
//        team.setTeamName("H");
//        teamService.saveTeam(team);


//        Cyclist cyclist = new Cyclist();
//        cyclist.setFirstName("Mohammed");
//        cyclist.setLastName("DOKHANA");
//        cyclist.setNationality("M");
//        cyclist.setAge("28");
//        Team team = new Team();
//        team.setTeamId(UUID.fromString("21654fe6-488f-48b9-a2db-7e2592b4e442"));
//        cyclist.setTeam(team);
//        cyclistService.saveCyclist(cyclist);

        Stage stage = new Stage();
        stage.setStageType("Contre le montre");
        stage.setStartLocation("Marrakesh");
        stage.setEndLocation("Casablanca");
        stage.setStartDate(LocalDate.of(2024, 10, 1));

        stageService.createStage(stage);

//        List<Stage> stagesByCompetitionId = stageService.getStagesByCompetitionId(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));
//        stagesByCompetitionId.forEach(c -> {
//            System.out.print(c.getStageNumber());
//            System.out.println();
//        });

    }


}