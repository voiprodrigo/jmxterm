package org.cyclopsgroup.jmxterm;

import org.apache.commons.lang3.StringUtils;
import org.cyclopsgroup.jcli.annotation.Argument;
import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jcli.annotation.MultiValue;

import java.util.List;

/**
 * A command for testing that records parameters passed in
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Cli(name = "test", description = "desc")
public class SelfRecordingCommand extends Command {
  private List<String> arguments;

  private final List<Command> records;

  /**
   * @param records List of commands that gets passed in
   */
  public SelfRecordingCommand(List<Command> records) {
    this.records = records;
  }

  @Override
  public void execute() {
    records.add(this);
  }

  /**
   * @return Arguments
   */
  public String getArgs() {
    return StringUtils.join(arguments, ' ');
  }

  /**
   * @return Array of arguments
   */
  public List<String> getArguments() {
    return arguments;
  }

  /**
   * @param arguments Arguments
   */
  @MultiValue
  @Argument
  public void setArguments(List<String> arguments) {
    this.arguments = arguments;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + ":" + getArgs();
  }
}
