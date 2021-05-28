package cucumber;

import io.cucumber.java.en.Given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BackgroundStepDefinition {
    @Given("the docker container is running")
    public void theDockerContainerIsRunning() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src/test/resources/run_container.bat");
        TimeUnit.SECONDS.sleep(15);
    }
}
