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

class TizenBuildTask extends TizenTask{

    StringBuilder sout = new StringBuilder();
    StringBuilder serr = new StringBuilder();

    @TaskAction
        protected void process() {
            Tizen tizen = project.tizen;
            tizen.dump();
            build();
        }


    private void build() {
        Tizen tizen = project.tizen;
        String args = " build-native";

        if(tizen.build_native.arch){
            args += " --arch " + tizen.build_native.arch;
        }
        if(tizen.build_native.compiler){
            args += " --compiler " + tizen.build_native.compiler;
        }
        if(tizen.build_native.configuration){
            args += " --configuration " + tizen.build_native.configuration;
        }
        if(tizen.build_native.jobs){
            args += " --jobs " + tizen.build_native.jobs;
        }

        args += " -- ./";

        super.exec(args);
    }
}


