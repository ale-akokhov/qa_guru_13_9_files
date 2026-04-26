package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {

    @Disabled
    @Test
    void pdfTest() throws Exception {
        Selenide.open("https://docs.junit.org/6.0.3/overview.html");
        File file = $("a[href*='junit-user-guide']").download();
        PDF pdf = new PDF(file);
        assertThat(pdf.title).isEqualTo("JUnit");
    }

    @Disabled
    @Test
    void xlsTest() throws Exception {
        Selenide.open("https://romashka2008.ru/");
        File file = $$("a[href*='xls']").get(1).download();
        XLS xls = new XLS(file);
        assertThat(xls.excel.getSheetAt(0)
                .getRow(0).getCell(0)
                .getStringCellValue())
                .contains("Прайс-лист");
    }


    @Test
    void csvTest() {

    }

    @Disabled
    @Test
    void zipTest() {

    }

    @Disabled
    @Test
    void jsonTest() {

    }

}
