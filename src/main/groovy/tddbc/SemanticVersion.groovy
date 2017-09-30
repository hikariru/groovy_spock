package tddbc

class SemanticVersion {

    int major
    int minor
    int patch

    SemanticVersion(){
        this.major = 0
        this.minor = 0
        this.patch = 0
    }

    SemanticVersion(int major,int minor, int patch) {
        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException()
        }

        this.major = major
        this.minor = minor
        this.patch = patch
    }

    def getVersion() {
        return "${major}.${minor}.${patch}"
    }

    def isSameVersion(SemanticVersion semanticVersion) {
        return this.getVersion() == semanticVersion.getVersion()
    }

    void releasePatch() {
        this.patch++
    }

    def releaseMinorUpdate() {
        this.minor++
        this.patch = 0
    }
}
