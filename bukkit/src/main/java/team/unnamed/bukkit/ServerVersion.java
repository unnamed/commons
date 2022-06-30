package team.unnamed.bukkit;

import org.bukkit.Bukkit;

import java.util.regex.Pattern;

public class ServerVersion {

    public static final String VERSION_STRING = Bukkit.getServer()
            .getClass()
            .getName()
            .split(Pattern.quote("."))[3];

    public static final ServerVersion CURRENT = getVersionOfString(VERSION_STRING);

    private final byte major;
    private final byte minor;
    private final byte patch;

    public ServerVersion(byte major, byte minor, byte patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public byte getMajor() {
        return major;
    }

    public byte getMinor() {
        return minor;
    }

    public byte getPatch() {
        return patch;
    }

    @Override
    public String toString() {
        return "v" + major + '_' + minor + "_R" + patch;
    }

    /**
     * Resolves a {@link ServerVersion} from a given {@code versionString}
     * with the format v(major)_(minor)_R(patch)
     *
     * @throws NumberFormatException If major, minor or patch versions
     *                               are not bytes
     */
    public static ServerVersion getVersionOfString(String versionString) {
        String[] args = versionString.split(Pattern.quote("_"));
        byte major = Byte.parseByte(args[0].substring(1));
        byte minor = Byte.parseByte(args[1]);
        byte patch = Byte.parseByte(args[2].substring(1));
        return new ServerVersion(major, minor, patch);
    }

}
