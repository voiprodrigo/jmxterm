package org.cyclopsgroup.jmxterm.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

/**
 * Test case for {@link FileCommandOutput}
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class FileCommandOutputTest {
  private File testFile;

  /**
   * prepare for test output file
   */
  @Before
  public void setUpTestFile() {
    testFile = new File(
        SystemUtils.JAVA_IO_TMPDIR + "/test-" + RandomStringUtils.randomAlphabetic(20) + ".txt");
  }

  /**
   * Delete test file
   *
   * @throws IOException If file operation fails
   */
  @After
  public void tearDownTestFile() throws IOException {
    FileUtils.forceDelete(testFile);
  }

  /**
   * Writes out some output and verify result
   *
   * @throws IOException If file IO fails
   */
  @Test
  public void testWrite() throws IOException {
    FileCommandOutput output = new FileCommandOutput(testFile, false);
    output.println("helloworld");
    output.printMessage("say hello");
    output.close();

    assertEquals("helloworld",
        FileUtils.readFileToString(testFile, Charset.forName("UTF-8")).trim());
  }

  /**
   * Writes out some output and verify result
   *
   * @throws IOException If file IO fails
   */
  @Test
  public void testWriteMultipleTimes() throws IOException {
    FileCommandOutput output = new FileCommandOutput(testFile, false);
    output.println("helloworld");
    output.printMessage("say hello");
    output.close();

    FileCommandOutput output2 = new FileCommandOutput(testFile, true);
    output2.println("helloworld2");
    output2.printMessage("say hello2");
    output2.close();

    assertEquals("helloworld" + System.lineSeparator() + "helloworld2",
        FileUtils.readFileToString(testFile, Charset.forName("UTF-8")).trim());
  }
}
