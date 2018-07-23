package DatamodelTests;

import Datamodel.Answer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AnswerTest {

    private int testId = 13;
    private int testQuestionId = 4;
    private int testChosenAnswer1 = 2;
    private int testChosenAnswer2 = 3;
    private int testChosenAnswer3 = 0;
    private String testQuestionPhrase = "sampleQuestion";
    private String testGrammarSection = "sampleSection";
    private String testExercise = "sampleCise";

    private String testPossibleAnswer1 = "sample1";
    private String testPossibleAnswer2 = "sample2";

    private int testCorrectAnswer1 = 0;

    @Test
    public void testCreateAnswerObject() {
        Answer answer = new Answer();
        assertEquals(Answer.class, answer.getClass());
        ArrayList<String> possibleAnswers = new ArrayList<>();
        ArrayList<Integer> correctAnswers = new ArrayList<>();
        Answer answer2 = new Answer(testQuestionId, testQuestionPhrase, testGrammarSection, testExercise, possibleAnswers, correctAnswers);
        assertEquals(testQuestionId, answer2.getQuestionId());
    }

    @Test
    public void testGetterAnsSetterOfAnswerObject() {
        ArrayList<Integer> testChosenAnswers = new ArrayList<>();
        testChosenAnswers.add(testChosenAnswer1);
        testChosenAnswers.add(testChosenAnswer2);
        testChosenAnswers.add(testChosenAnswer3);
        Answer answer = new Answer();
        answer.setId(testId);
        answer.setQuestionId(testQuestionId);
        answer.setChosenAnswers(testChosenAnswers);
        assertEquals(testId, answer.getId());
        assertEquals(testQuestionId, answer.getQuestionId());
        assertEquals(testChosenAnswer1, (int) answer.getChosenAnswers().get(0));
        assertEquals(testChosenAnswer2, (int) answer.getChosenAnswers().get(1));
        assertEquals(testChosenAnswer3, (int) answer.getChosenAnswers().get(2));
    }

    @Test
    public void testAddChosenAnswer() {
        Answer answer = new Answer();
        answer.addChosenAnswer(testChosenAnswer1);
        answer.addChosenAnswer(testChosenAnswer2);
        answer.addChosenAnswer(testChosenAnswer3);
        assertEquals(testChosenAnswer1, (int) answer.getChosenAnswers().get(0));
        assertEquals(testChosenAnswer2, (int) answer.getChosenAnswers().get(1));
        assertEquals(testChosenAnswer3, (int) answer.getChosenAnswers().get(2));
    }

    @Test
    public void testAddPossibleAndCorrectAnswer() {
        Answer answer = new Answer();
        answer.addPossibleAnswer(testPossibleAnswer1);
        answer.addPossibleAnswer(testPossibleAnswer2);
        answer.addCorrectAnswer(testCorrectAnswer1);
        assertEquals(2, answer.getPossibleAnswers().size());
        assertEquals(1, answer.getCorrectAnswers().size());
    }
}
