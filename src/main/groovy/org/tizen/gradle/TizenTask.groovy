package org.tizen.gradle
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.StartParameter
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.JavaExec
import java.nio.file.Files
import java.nio.file.Paths
import static groovy.io.FileType.FILES

class TizenTask extends JavaExec {

    @Override
        void exec() {
            Tizen tizen = project.tizen;
            String sdk_path = null;

            if (tizen.logLevel)
                println("TizenTask: exec " + "start");
            configure(tizen);
            tizen.dump();

            // Search sdk path in local.properties 
            Properties props = new Properties();
            props.load(new FileInputStream("$project.rootDir/local.properties"));
            props.each { prop ->
                project.ext.set(prop.key, prop.value);
                //println("prop.key: " + prop.key); println("prop.value: " + prop.value);
            }
            sdk_path = project.ext.get('sdk.dir');

            // Search sdk path in Environments
            if ( sdk_path == null ){
                sdk_path = System.getenv('TIZEN_SDK')
            }

            if ( sdk_path == null || !Files.exists(Paths.get(sdk_path))) {
                throw new InvalidUserDataException("Invalid Tizen SDK path: " + sdk_path)
            }

            String command = sdk_path + "/tools/ide/bin/tizen ${tizen.sdk.args}";

            if (tizen.logLevel){
                println("TizenTask: command: " + command);
            }

            int exit;
            StringBuilder sout = new StringBuilder();
            StringBuilder serr = new StringBuilder();

            def proc = command.split().toList().execute();
            proc.consumeProcessOutput(sout, serr);
            proc.waitFor();

            exit = proc.exitValue();
            if (tizen.logLevel){
                println("stdandard output of tizen command: \n$sout");
            }
            println("stdandard error of tizen command: \n$serr");
        }

    def configure(Tizen tizen) {
        if (tizen.logLevel)
            println("TizenTask: configure " + "start");
        if (tizen.logLevel)
            println("TizenTask: configure " + "end");
    }
}
