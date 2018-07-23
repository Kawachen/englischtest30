package ResultLogic;

import Datamodel.Answer;
import Datamodel.Result;

import com.itextpdf.text.Document;
import java.util.ArrayList;

public interface ResultLogic {

    Result getUserResultForTheseAnswers(ArrayList<Answer> answers);

    void createPDFDocumentForResult(Document pdfDocument, Result result, long testWorkingTime) throws com.itextpdf.text.DocumentException;
}
