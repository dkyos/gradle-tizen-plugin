package org.tizen.gradle
import org.gradle.util.ConfigureUtil

class Tizen {

    class SDK {
        String sdkPath;
        String args;
    }
    SDK sdk = new SDK();
    def sdk(Closure closure) {
        ConfigureUtil.configure(closure, sdk, false)
    }
    def sdk_dump() {
        println("sdk.sdkPath:   " + sdk.sdkPath);
    }

    class APP {
        String type;
        String platform;
    }
    APP app = new APP();
    def app(Closure closure) {
        ConfigureUtil.configure(closure, app, false)
    }
    def app_dump() {
        println("app.type:   " + app.type);
        println("app.platform:   " + app.platform);
    }

    class NativeBuild{
        String arch;
        String compiler;
        String configuration;
        int jobs;
        String rootstrap;
    }
    NativeBuild build_native = new NativeBuild();
    def build_native(Closure closure) {
        ConfigureUtil.configure(closure, build_native, false)
    }
    def build_native_dump() {
        println("build_native.arch:  " + build_native.arch);
        println("build_native.compiler:  " + build_native.compiler);
        println("build_native.configuration:  " + build_native.configuration);
        println("build_native.jobs:  " + build_native.jobs);
        println("build_native.rootstrap:  " + build_native.rootstrap);
    }

    def dump() {
        println("=========================================");
        sdk_dump();
        app_dump();
        build_native_dump();
        println("=========================================");
    }

}
