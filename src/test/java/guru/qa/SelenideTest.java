package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideTest {

    @Disabled
    @Test
    void downloadTest () throws Exception {
        Selenide.open("https://github.com/junit-team/junit-framework/blob/main/README.md");
        File file = $("[data-testid = 'raw-button']").download();
        try (InputStream is = new FileInputStream(file)) {
            byte[] fileContent = is.readAllBytes();
            assertThat(new String(fileContent, UTF_8)).contains("Contributions to JUnit");
        }
    }

    @Test
    void uploadTest () {
        Selenide.open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("Files/1.txt");
        //$(".row").shouldHave(text("1.txt"));
        $("#file-submit").click();
        $(".example").shouldHave(text("File Uploaded!"))
                .shouldHave(text("1.txt"));

    }
}
