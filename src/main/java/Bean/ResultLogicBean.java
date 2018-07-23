package Bean;

import Datamodel.Answer;
import Datamodel.Result;
import Datamodel.User;
import ResultLogic.ImplementedResultLogic;
import ResultLogic.ResultLogic;
import Services.Answer.AnswerService;
import Services.User.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class ResultLogicBean {

    private User user;
    private Result result = new Result();
    private AnswerService answerService;
    private ResultLogic resultLogic = new ImplementedResultLogic();

    public ResultLogicBean() {
        UserService userService = new UserService();
        this.user = userService.getUserByEmailAddress(SessionUtils.getSession().getAttribute("email").toString());
        answerService = new AnswerService(user);

        ArrayList<Answer> answers = this.answerService.getAnswers();

        this.result = resultLogic.getUserResultForTheseAnswers(answers);
    }

    public Result getResult() {
        return this.result;
    }

    public long getTestWorkingTime() {
        return this.answerService.getTestWorkingTimeByUserId(this.user.getId());
    }

    public void downloadFile() {
        Document document = new Document();
        try {
            //Get response and outputStream
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, servletOutputStream);

            resultLogic.createPDFDocumentForResult(document, result, getTestWorkingTime());

            //set response information
            response.setHeader("Content-Disposition", "attachment;filename=result.pdf");
            response.setContentType("application/pdf");

            //close outputStream and complete response
            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch(Exception e) {
            System.err.println("Shit");
            e.printStackTrace();
        }
    }
}
