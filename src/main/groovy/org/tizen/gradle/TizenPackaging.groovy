package org.tizen.gradle

class TizenPackaging extends TizenTask {

    @Override
        def configure(Tizen tizen) {
            super.configure(tizen)
            tizen.sdk.args = "package";

            if(tizen.packaging.type){
                tizen.sdk.args += " --type " + tizen.packaging.type;
            }
            if(tizen.packaging.profileName){
                tizen.sdk.args += " --sign " + tizen.packaging.profileName;
            }
            if(tizen.packaging.strip){
                tizen.sdk.args += " --strip " + tizen.packaging.strip;
            }

            tizen.sdk.args += " -- " + tizen.packaging.buildDir;

        }
}
