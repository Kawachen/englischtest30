package LoggerTests;
import Logger.QuestionLogicLogger;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {

    @Test
    public void testGetInstance() {
        QuestionLogicLogger questionLogicLogger = QuestionLogicLogger.getInstance();
        assertNotNull(questionLogicLogger);
    }

    @Test
    public void testSetPreviouslyChangedMarker() {
        QuestionLogicLogger questionLogicLogger = QuestionLogicLogger.getInstance();
        questionLogicLogger.setPreviouslyChangedMarker();
        assertTrue(questionLogicLogger.doesAnyQuestionGotPreviouslyChanged());
    }

    @Test
    public void registerAndLogoutQuestionLogics() {
        QuestionLogicLogger questionLogicLogger = QuestionLogicLogger.getInstance();
        int logicId = questionLogicLogger.registerNewQuestionLogic();
        questionLogicLogger.logoutQuestionLogic(logicId);
    }

    @Test
    public void removePreviouslyChangedMarkerWhenLastQuestionLogicLogsOut() {
        QuestionLogicLogger questionLogicLogger = QuestionLogicLogger.getInstance();
        int logicId = questionLogicLogger.registerNewQuestionLogic();
        int logicId2 = questionLogicLogger.registerNewQuestionLogic();
        questionLogicLogger.setPreviouslyChangedMarker();
        assertTrue(questionLogicLogger.doesAnyQuestionGotPreviouslyChanged());
        questionLogicLogger.logoutQuestionLogic(logicId);
        questionLogicLogger.logoutQuestionLogic(logicId2);
        assertFalse(questionLogicLogger.doesAnyQuestionGotPreviouslyChanged());
    }
}
