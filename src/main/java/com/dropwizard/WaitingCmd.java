package com.dropwizard;

import com.dropwizard.config.ProjectConfig;
import io.dropwizard.Application;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;

public class WaitingCmd extends EnvironmentCommand<ProjectConfig> {


    protected WaitingCmd(Application<ProjectConfig> application) {
        super(application, "wait", "wait for a second");
    }

    @Override
    protected void run(Environment environment, Namespace namespace, ProjectConfig configuration) throws Exception {
        System.out.println("Starting command..................................");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished running command..........................");
    }

}
