package org.tizen.gradle
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.InvalidUserDataException
import org.gradle.StartParameter
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.tasks.JavaExec
import java.nio.file.Files
import java.nio.file.Paths
import static groovy.io.FileType.FILES

class TizenPackageTask extends TizenTask{

    StringBuilder sout = new StringBuilder();
    StringBuilder serr = new StringBuilder();
    String command = project.ext.get('sdk.dir') + "/tools/ide/bin/tizen";

    @TaskAction
        protected void process() {
            Tizen tizen = project.tizen;
            tizen.dump();

            packaging();
        }


    private void packaging() {
        Tizen tizen = project.tizen;
        String args = "package";

        if(tizen.packaging.type){
            args += " --type " + tizen.packaging.type;
        }
        if(tizen.packaging.profileName){
            args += " --sign " + tizen.packaging.profileName;
        }
        if(tizen.packaging.strip){
            args += " --strip " + tizen.packaging.strip;
        }

        args += " -- " + tizen.packaging.buildDir;
        command += " ${args}";

        super.exec(command);
    }
}

