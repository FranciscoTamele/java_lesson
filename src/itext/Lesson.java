package itext;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

import static com.itextpdf.styledxmlparser.css.CommonCssConstants.BOLD;

public class Lesson {

    private static PdfWriter writer;

    public static void main(String[] args) {

        try{

//            criarDocumentoPDF();

//            createListItemsPDF();

//            createTextImagesPDF();

            createTable();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private static void criarDocumentoPDF(){

        try {
            writer = new PdfWriter("firstdoc.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);


            /**
             * Quando o paragrafo preenche toda linha passa para nova linha como e comum.
             * Quando a pagina esta cheia o documento cria nova pagina e comeca a preencher como esperado
             */

            for(int i=0;i<10;i++){
                document.add(new Paragraph("Francisco Rafael Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele Francisco Tamele"+String.valueOf(i)));
            }


            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void createListItems() throws FileNotFoundException {

        writer = new PdfWriter("firstdoc.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf,PageSize.A4);
// Create a PdfFont
//        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
// Add a Paragraph
        document.add(new Paragraph("iText is:"));
// Create a List
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022");
// Add ListItem objects
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));
// Add the list
        document.add(list);
        document.close();
    }

    private static void createTextImagesPDF() throws IOException {


        writer = new PdfWriter("firstdoc.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf,PageSize.A4);

        File file = new File("mipv_pass.jpg");
        byte[] fileContent = Files.readAllBytes(file.toPath());

        Image fox = new Image(ImageDataFactory.create(fileContent));
        Paragraph p = new Paragraph("The quick brown ")
                .add(fox)
                .add(" jumps over the lazy ");
        document.add(p);
        document.close();

    }

    private static void createTable() throws IOException {

        writer = new PdfWriter("firstdoc.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        Table table = new Table(new float[]{2,1,1,1,1,1});
        table.setWidth(UnitValue.createPercentValue(100));

        String headers[] = {"Code","Nome cliente","Telefone cliente","Data e Hora","Ammount","Status"};
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
        PdfFont normalFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        table.setFont(normalFont);
        table.setFontSize(8);
        table.setTextAlignment(TextAlignment.CENTER);




        for (String title : headers) {
            table.addCell(title);
        }

        for(int i=0;i<table.getNumberOfColumns();i++){
            table.getCell(0,i).setFont(boldFont).setFontColor(ColorConstants.WHITE).setBackgroundColor(ColorConstants.RED);
        }

        for (int i=0;i<headers.length;i++){
            table.addCell(headers[i].concat(String.valueOf(i)));
        }

        document.add(table);
        document.close();
    }

}
