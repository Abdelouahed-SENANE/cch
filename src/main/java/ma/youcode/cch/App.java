package ma.youcode.cch;


import jakarta.validation.constraints.Past;
import ma.youcode.cch.entity.*;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.entity.embedded.ResultId;
import ma.youcode.cch.service.interfaces.*;
import ma.youcode.cch.utils.DateTimeParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

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


        UUID cyclistId = UUID.fromString("ae4e6f37-9e73-4073-b9a3-96fb33d53622");
        UUID competitionId = UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b");
//
//        try {
//            resultService.createResult(cyclistId, competitionId);
//            System.out.println("This cyclist can added on this stage ");
//        }catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//        ResultId resultId = new ResultId();
//        resultId.setStageId(UUID.fromString("1c18bf4b-0312-4096-96a8-51d7b2a28503"));
//        resultId.setCyclistId(UUID.fromString("da346d6b-c80e-4701-88ce-7943bbbe284a"));
//        Result result = new Result();
//        result.setResultId(resultId);
//        result.setDuration(Duration.ofSeconds(100));
//        resultService.updateResult(result);
//        Competition competition = new Competition();
//        competition.setCompetitionId(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));
//        Cyclist cyclist = new Cyclist();
//        cyclist.setCyclistId(UUID.fromString("da346d6b-c80e-4701-88ce-7943bbbe284a"));
////
//        Competition competition = new Competition();
//        competition.setCompetitionId(competitionId);
//        Cyclist cyclist = new Cyclist();
//        cyclist.setCyclistId(cyclistId);
//        GeneralResultId generalResultId = new GeneralResultId();
//        generalResultId.setCompetitionId(competitionId);
//        generalResultId.setCyclistId(cyclistId);
//        GeneralResult generalResult = new GeneralResult();
//        generalResult.setGeneralResultId(generalResultId);
//        generalResult.setCompetition(competition);
//        generalResult.setCyclist(cyclist);
//        generalResult.setGeneralRank(0);
//        generalResult.setDuration(Duration.ofMinutes(100));
//        generalResultService.createGeneralResult(generalResult);


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

//        Stage stage = new Stage();
//        stage.setStageId(UUID.fromString("22f7023b-f33f-45b8-9f40-8426c8b91178"));
//        stage.setStageType("Contre le monde");
//        stage.setStartLocation("Munich");
//        stage.setEndLocation("London");
//        Competition competition = new Competition();
//        competition.setCompetitionId(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));
//        stage.setCompetition(competition);
//        stage.setStartDate(LocalDate.of(2024, 10, 1));
//
//        stageService.updateStage(stage);

//        List<Stage> stagesByCompetitionId = stageService.getStagesByCompetitionId(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));
//        stagesByCompetitionId.forEach(c -> {
//            System.out.print(c.getStageNumber());
//            System.out.println();
//        });

        Optional<Stage> stageOptional = stageService.getStage(UUID.fromString("1c18bf4b-0312-4096-96a8-51d7b2a28503"));
        if (stageOptional.isPresent())
        {
            Stage stage = stageOptional.get();
            System.out.println(stage.getCompetition().getCompetitionName());
            System.out.println(stage.getStageNumber());
            System.out.println("Classement for this stage ");
            for (Result result : stage.getResults()) {
                System.out.println(result.getCyclist().getFirstName() + "His classement is " + result.getStageRank());
            }
        }
    }


}