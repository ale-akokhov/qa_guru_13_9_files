package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {

    @Test
    void pdfTest() throws Exception {
        Selenide.open("https://docs.junit.org/6.0.3/overview.html");
        File file = $("a[href*='junit-user-guide']").download();
        PDF pdf = new PDF(file);
        assertThat(pdf.title).isEqualTo("JUnit");
    }


    @Test
    void xlsTest() {

    }

    @Test
    void csvTest() {

    }

    @Test
    void zipTest() {

    }

    @Test
    void jsonTest() {

    }

}
