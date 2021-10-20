package team.unnamed.bukkit;

import org.bukkit.Bukkit;

public final class ServerVersionUtils {

    public static final String SERVER_VERSION = Bukkit.getServer()
            .getClass().getPackage()
            .getName().split("\\.")[3]
            .substring(1);

    public static final int SERVER_VERSION_INT = Integer.parseInt(
            SERVER_VERSION.replace("1_", "")
                    .replaceAll("_R\\d", "")
    );

    private ServerVersionUtils() {
        throw new UnsupportedOperationException();
    }

}
