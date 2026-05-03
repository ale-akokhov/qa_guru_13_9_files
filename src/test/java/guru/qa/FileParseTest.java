package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import guru.qa.domain.Teacher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {

    ClassLoader classLoader = FileParseTest.class.getClassLoader();


    @Test
    void pdfTest() throws Exception {
        Selenide.open("https://docs.junit.org/6.0.3/overview.html");
        File file = $("a[href*='junit-user-guide']").download();
        PDF pdf = new PDF(file);
        assertThat(pdf.title).isEqualTo("JUnit");
    }


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
    void csvTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("example.csv");
        CSVReader csvReader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        List<String[]> csv = csvReader.readAll();
        assertThat(csv).contains(
                new String[] {"teacher","lesson","date"},
                new String[] {"Tuchs","junit","03.06"},
                new String[] {"Eroshenko","allure","07.06"}
        );
        is.close();
    }


    @Test
    void zipTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("sample.zip");
        ZipInputStream zis = new ZipInputStream(is, StandardCharsets.UTF_8);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            assertThat(entry.getName()).isEqualTo("sample.pdf");
        }
        is.close();
    }



    @Test
    void jsonTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("teacher.json");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new InputStreamReader(is), JsonObject.class);
        assertThat(jsonObject.get("name").getAsString()).isEqualTo("Dmitrii");
        assertThat(jsonObject.get("isGoodTeacher").getAsBoolean()).isEqualTo(true);
        assertThat(jsonObject.get("age").getAsInt()).isEqualTo(33);
        is.close();

        new Teacher.Passport();


    }@Test
    void jsonTestNG() throws Exception {
        InputStream is = classLoader.getResourceAsStream("teacher.json");
        Gson gson = new Gson();
        Teacher jsonObject = gson.fromJson(new InputStreamReader(is), Teacher.class);
        assertThat(jsonObject.getName()).isEqualTo("Dmitrii");
        assertThat(jsonObject.getGoodTeacher()).isEqualTo(true);
        assertThat(jsonObject.getAge()).isEqualTo(33);
        assertThat(jsonObject.getPassport().getNumber()).isEqualTo(1234);
        is.close();

    }

}
