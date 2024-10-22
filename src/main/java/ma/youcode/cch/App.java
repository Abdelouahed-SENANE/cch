package ma.youcode.cch;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//        TeamService teamService = context.getBean(TeamService.class);
//        CyclistService cyclistService = context.getBean(CyclistService.class);
//        CompetitionService competitionService = context.getBean(CompetitionService.class);
//        StageService stageService = context.getBean(StageService.class);
//        GeneralResultService generalResultService = context.getBean(GeneralResultService.class);
//        ResultService resultService = context.getBean(ResultService.class);
//
//
//        UUID cyclistId = UUID.fromString("8a612246-5284-43ec-8619-795f09a3bd1c");
//        UUID competitionId = UUID.fromString("c25768dd-51f8-43af-ba82-c85b6c09c30a");
//        UUID stageId = UUID.fromString("2a33a503-7f8b-4377-a78e-59b8a4752f1c");
////
//        ResultId resultId = new ResultId();
//        resultId.setCyclistId(cyclistId);
//        resultId.setStageId(stageId);
//        StageResult result = new StageResult();
//        result.setResultId(resultId);
//        try {
//            resultService.createResult(result);
//            System.out.println("This cyclist can added on this stage ");
//        }catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//        ResultId resultId = new ResultId();
//        resultId.setStageId(UUID.fromString("1c18bf4b-0312-4096-96a8-51d7b2a28503"));
//        resultId.setCyclistId(UUID.fromString("da346d6b-c80e-4701-88ce-7943bbbe284a"));
//        StageResult result = new StageResult();
//        result.setResultId(resultId);
//        result.setDuration(Duration.ofSeconds(100));
//        resultService.updateResult(result);
//        Competition competition = new Competition();
//        competition.setCompetitionId(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));
//        Cyclist cyclist = new Cyclist();
//        cyclist.setCyclistId(UUID.fromString("da346d6b-c80e-4701-88ce-7943bbbe284a"));
////
//        Competition competition = new Competition();
//        competition.setCompetitionName("Tokyo");
//        competition.setStartDate(LocalDate.of(2024, 10, 1));
//        competition.setEndDate(LocalDate.of(2024, 12, 1));
//        competition.setYear(Year.of(2024));
//        competition.setNumberOfStage(3);
//        competitionService.createCompetition(competition);


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
//        generalResult.setOverallRank(0);
//        generalResult.setOverallDuration(Duration.ofMinutes(100));
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
//        team.setTeamName("B");
//        teamService.createTeam(team);


//        Cyclist cyclist = new Cyclist();
//        cyclist.setFirstName("Salah");
//        cyclist.setLastName("LAKHDAR");
//        cyclist.setNationality("A");
//        cyclist.setAge("30");
//        Team team = new Team();
//        team.setTeamId(UUID.fromString("5e3c2a98-f705-4fee-9d31-3bdd9c52648a"));
//        cyclist.setTeam(team);
//        cyclistService.createCyclist(cyclist);

//        Stage stage = new Stage();
////        stage.setStageId(UUID.fromString("22f7023b-f33f-45b8-9f40-8426c8b91178"));
//        stage.setStageType("Contre le monde");
//        stage.setStartLocation("Munich");
//        stage.setEndLocation("Paris");
//        Competition competition = new Competition();
//        competition.setCompetitionId(UUID.fromString("c25768dd-51f8-43af-ba82-c85b6c09c30a"));
//        stage.setCompetition(competition);
//        stage.setStartDate(LocalDate.of(2024, 10, 1));
//       stageService.createStage(stage);

//        List<Stage> stagesByCompetitionId = stageService.getStagesByCompetitionId(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));
//        stagesByCompetitionId.forEach(c -> {
//            System.out.print(c.getStageNumber());
//            System.out.println();
//        });

//        Optional<Stage> stageOptional = stageService.getStageWithResultOrderedByDuration(UUID.fromString("1c18bf4b-0312-4096-96a8-51d7b2a28503"));
//        if (stageOptional.isPresent())
//        {
//            Stage stage = stageOptional.get();
//            System.out.println(stage.getCompetition().getCompetitionName());
//            System.out.println(stage.getStageNumber());
//            System.out.println("Classement for this stage ");
//            for (StageResult result : stage.getStageResults()) {
//                System.out.println(result.getCyclist().getFirstName() + " His classement is " + result.getStageRank());
//            }
//        }
    }


}