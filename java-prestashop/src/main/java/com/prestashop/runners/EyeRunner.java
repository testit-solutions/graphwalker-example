package com.prestashop.runners;

import com.prestashop.modelimplementation.EyeImpl;
import org.graphwalker.core.statistics.Execution;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EyeRunner {
    private static Logger logger = LoggerFactory.getLogger(EyeRunner.class);

    public static void main(String[] args) throws IOException {
        TestExecutor executor = new TestExecutor(
                EyeImpl.class
        );

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                logger.error(error);
            }
        }
        logger.info("Done: [" + result.getResults().toString(2) + "]");
        int step = 1;
        for (Execution execution : executor.getMachine().getProfiler().getExecutionPath()) {
            logger.info("Step {}: {}", step++, execution.getElement().getName());
        }
    }
}
