package org.tizen.gradle
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.StartParameter
import static groovy.io.FileType.FILES
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.JavaExec
import java.nio.file.Files
import java.nio.file.Paths

class TizenTask extends JavaExec {

    @Override
        void exec() {
            Tizen tizen = project.tizen;

            println("TizenTask: exec " + "start");
            configure(tizen);

            tizen.dump();

            if (tizen.sdk.sdkPath == null || !Files.exists(Paths.get(tizen.sdk.sdkPath))) {
                throw new InvalidUserDataException("Invalid Tizen sdkPath: " + tizen.sdk.sdkPath)
            }

            println("TizenTask: args " + tizen.sdk.args);

            String command = "${tizen.sdk.sdkPath}/tools/ide/bin/tizen ${tizen.sdk.args}";
            int exit;
            StringBuilder sout = new StringBuilder();
            StringBuilder serr = new StringBuilder();

            println("TizenTask: command :" + command);

            def proc = command.split().toList().execute();
            proc.consumeProcessOutput(sout, serr);
            proc.waitFor();

            exit = proc.exitValue();
            println ("stdout: $sout"); println ("stderr: $serr");
        }

    def configure(Tizen tizen) {
            println("TizenTask: configure " + "start");
            println("TizenTask: configure " + "end");
    }
}
