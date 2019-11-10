package seedu.address.model.pdfmanager.pdfdeliveryorder;

import java.util.List;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import seedu.address.model.company.Company;
import seedu.address.model.task.Task;

/**
 * Represents a list of delivery orders in PDF format.
 */
public class PdfDeliveryOrder {

    //A4 size is 595 x 842
    public static final Rectangle COMPANY_HOLDER = new Rectangle(30, 710, 520, 120);
    public static final Rectangle CUSTOMER_HOLDER = new Rectangle(30, 550, 260, 120);
    public static final Rectangle DELIVERY_NUMBER_HOLDER = new Rectangle(400, 550, 150, 145);

    private PdfDocument pdfDocument;
    private List<Task> tasks;
    private Company company;

    public PdfDeliveryOrder(PdfDocument pdfDocument, List<Task> tasks, Company company) {
        this.pdfDocument = pdfDocument;
        this.tasks = tasks;
        this.company = company;
    }

    /**
     * Creates delivery orders for the delivery tasks.
     */
    public void create() {
        for (Task task : tasks) {
            PdfPage newPage = pdfDocument.addNewPage();
            PdfCanvas pdfCanvas = new PdfCanvas(newPage);

            List<PdfCanvasLayout> allCanvas = List.of(
                    new PdfCompanyCanvas(pdfCanvas, pdfDocument, COMPANY_HOLDER, company),
                    new PdfCustomerCanvas(pdfCanvas, pdfDocument, CUSTOMER_HOLDER, task.getCustomer()),
                    new PdfDeliveryNumberCanvas(pdfCanvas, pdfDocument, DELIVERY_NUMBER_HOLDER, task));

            allCanvas.forEach(PdfCanvasLayout::generate);

        }

    }
}
