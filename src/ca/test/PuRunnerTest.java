package ca.test;

import ca.java.PuRunner;
import org.junit.Assert;
import org.junit.Test;

public class PuRunnerTest {
    @Test
    public void testConstructor() {
        final String expectedDomain = "ruw2k8r2uim_domain";
        PuRunner puRunner = new PuRunner("userName", "password",
                "/ruw2k8r2uim_domain/ruw2k8r2uim_hub/ruw2k8r2uim", false);
        Assert.assertTrue(String.format("Expected domain %s got %s", expectedDomain, puRunner.getDomain()),
                puRunner.getDomain().equals(expectedDomain));
    }
}
