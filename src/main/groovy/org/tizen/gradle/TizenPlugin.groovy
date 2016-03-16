package org.tizen.gradle
import org.gradle.api.Plugin
import org.gradle.api.Project

class TizenPlugin implements Plugin<Project> {

    @Override
        void apply(Project project) {

            println("TizenPlugin: apply " + "start");

            if (project.extensions.findByName("tizen") == null) {
                project.extensions.create("tizen", Tizen)
            }

            project.task("build", type: TizenBuild);
            project.task("package", type: TizenPackaging);

            println("TizenPlugin: apply " + "end");
        }

}
