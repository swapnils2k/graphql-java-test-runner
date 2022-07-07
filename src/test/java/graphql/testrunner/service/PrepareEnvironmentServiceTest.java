package graphql.testrunner.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrepareEnvironmentServiceTest {

    @Mock
    private GitService gitService;

    @Mock
    private CommandExecutorService commandExecutorService;

    @InjectMocks
    private PrepareEnvironmentService prepareEnvironmentService;

    private static final String GRAPHQL_DIR = "/app/graphql-java/";
    private static final List<String> BUILD_GRAPHQL_JMH_JAR = asList("sh", "-c", "RELEASE_VERSION=test-runner-jmh ./gradlew jmhJar");

    @Test
    void prepareJars() {
        prepareEnvironmentService.prepareJar("8abc12345fdfd");

        verify(gitService).checkout(eq("8abc12345fdfd"));
        verify(commandExecutorService).executeCommandInDir(eq(BUILD_GRAPHQL_JMH_JAR), eq(GRAPHQL_DIR));
    }
}
