package ResultLogic;

import Datamodel.Answer;
import Datamodel.MistakeAnswer;
import Datamodel.Result;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;

public class ImplementedResultLogic implements ResultLogic {

    @Override
    public Result getUserResultForTheseAnswers(ArrayList<Answer> answers) {
        Result result = new Result();
        for(int i = 0;i < answers.size();i++) {
            for (int correctAnswer: answers.get(i).getCorrectAnswers()) {
                boolean mistake = true;
                for(int chosenAnswer: answers.get(i).getChosenAnswers()) {
                    if(correctAnswer == chosenAnswer) {
                        mistake = false;
                    }
                }
                if(mistake) {
                    MistakeAnswer mistakeAnswer = new MistakeAnswer(answers.get(i).getQuestionId(), answers.get(i).getQuestionPhrase(), answers.get(i).getChosenAnswers(), answers.get(i).getCorrectAnswers(), answers.get(i).getPossibleAnswers(), answers.get(i).getGrammarSection(), answers.get(i).getExercise());
                    result.addMistakeAnswer(mistakeAnswer);
                }
            }
        }
        return result;
    }

    @Override
    public void createPDFDocumentForResult(Document pdfDocument, Result result, long testWorkingTime) throws com.itextpdf.text.DocumentException {
        pdfDocument.open();
        pdfDocument.add(new Paragraph("Deine Test-Ergebnisse"));
        pdfDocument.add(new Paragraph("Deine Bearbeitungszeit in Millisekunden: "+testWorkingTime));
        pdfDocument.add(new Paragraph("Fehler insgesammt: "+result.getCountTotalMistakes()));
        pdfDocument.add(new Paragraph("\n"));
        float[] columnWidth = {1,4,3,4,3,3,3};
        PdfPTable table = new PdfPTable(columnWidth);
        table.setWidthPercentage(100);
        table.addCell(getClassicTextCell("ID:"));
        table.addCell(getClassicTextCell("Frage:"));
        table.addCell(getClassicTextCell("Grammatik:"));
        table.addCell(getClassicTextCell("Antwortmöglichkeiten:"));
        table.addCell(getClassicTextCell("Richtige Antwort/en"));
        table.addCell(getClassicTextCell("Deine Antwort/en"));
        table.addCell(getClassicTextCell("Übungen:"));
        for(MistakeAnswer answer:result.getMistakeAnswers()) {
            table.addCell(getClassicTextCell(Integer.toString(answer.getQuestionId())));
            table.addCell(getClassicTextCell(answer.getQuestionPhrase()));
            table.addCell(getClassicTextCell(answer.getGrammarSection()));
            int i = 1;
            StringBuilder possibleAnswerString = new StringBuilder();
            for(String possibleAnswer:answer.getPossibleAnswers()) {
                possibleAnswerString.append(i+". "+possibleAnswer+"\n");
                i++;
            }
            table.addCell(getClassicTextCell(possibleAnswerString.toString()));
            StringBuilder correctAnswerString = new StringBuilder();
            for(int correctAnswer:answer.getCorrectAnswers()) {
                correctAnswerString.append(Integer.toString(correctAnswer+1)+". "+answer.getPossibleAnswers().get(correctAnswer)+"\n");
            }
            table.addCell(getClassicTextCell(correctAnswerString.toString()));
            StringBuilder chosenAnswerString = new StringBuilder();
            for(int chosenAnswer: answer.getChosenAnswers()) {
                chosenAnswerString.append(Integer.toString(chosenAnswer+1)+". "+answer.getPossibleAnswers().get(chosenAnswer)+"\n");
            }
            table.addCell(getClassicTextCell(chosenAnswerString.toString()));
            table.addCell(getClassicTextCell(answer.getExercise()));
        }
        pdfDocument.add(table);
        pdfDocument.close();
    }

    private PdfPCell getClassicTextCell(String text) {
        Font classicFont = FontFactory.getFont("Arial", 10);
        return new PdfPCell(new Paragraph(text, classicFont));
    }
}
