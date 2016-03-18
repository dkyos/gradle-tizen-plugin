package org.tizen.gradle
import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.plugins.JavaPlugin


/**
 * Gradle plugin that extends the Java plugin for Tizen development.
 *
 * @author Dongkyun Yun(dkyun77@gmail.com)
 */
class TizenPlugin implements Plugin<Project> {

    public static final TIZEN_BUILD_TASK_NAME = "tizenBuild";
    public static final TIZEN_PACKAGE_TASK_NAME = "tizenPackage";

    private static final TIZEN_GROUP = "Tizen";

    private tizenBuildTask, tizenPackageTask;

    private Project project;
    private logger;

    @Override
        void apply(Project project) {
            project.plugins.apply(JavaPlugin.class);

            println("Tizen Plugin Loaded");

            this.project = project;
            this.logger = project.logger;

            if (project.extensions.findByName("tizen") == null) {
                project.extensions.create("tizen", Tizen)
            }

            tizenSetup();
            defineTasks();
        }

    private void tizenSetup() {

        println("Tizen Plugin setup");

        determineTizenDirs();
    }

    private void determineTizenDirs() {
        def sdkDir;

        // Determine the sdkDir value.
        // First, let's try the sdk.dir property in local.properties file.
        Properties props = new Properties();
        props.load(new FileInputStream("$project.rootDir/local.properties"));
        props.each { prop ->
            project.ext.set(prop.key, prop.value);
            //println("prop.key: " + prop.key); println("prop.value: " + prop.value);
        }
        sdkDir = project.ext.get('sdk.dir');

        if (sdkDir == null || sdkDir.length() == 0) {
            // No local.properties and/or no sdk.dir property: let's try TIZEN_HOME
            sdkDir = System.getenv("TIZEN_HOME");
            // Propagate it to the Gradle's environment
            if (sdkDir != null) {
                project.ext.set("sdk.dir", sdkDir);
            }
        }

        // Check for sdkDir correctly valued, and in case throw an error
        if (sdkDir == null || sdkDir.length() == 0) {
            throw new MissingPropertyException("Unable to find location of Tizen SDK. Please read documentation.");
        }
    }

    private void defineTasks() {
        defineTizenBuildTask();
        defineTizenPackageTask();

        //project.task("build", type: TizenBuild);
        //project.task("package", type: TizenPackaging);
    }

    private void defineTizenBuildTask() {
        tizenBuildTask = project.task(
                TIZEN_BUILD_TASK_NAME,
                group: TIZEN_GROUP,
                description: "Build the Tizen native application",
                type: TizenBuildTask);
    }

    private void defineTizenPackageTask() {
        tizenPackageTask = project.task(
                TIZEN_PACKAGE_TASK_NAME,
                group: TIZEN_GROUP,
                description: "Package the Tizen native application",
                type: TizenPackageTask);
    }
}
