package org.aqu0ryy.spawnerchance.utils;

import net.milkbowl.vault.permission.Permission;
import org.aqu0ryy.spawnerchance.Loader;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultUtil {

    private static Permission perms = null;

    public static boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = Loader.getInst().getServer().getServicesManager().getRegistration(Permission.class);

        if (rsp == null) {
            return false;
        }

        perms = rsp.getProvider();
        return true;
    }

    public static String getGroup(Player player) {
        return perms.getPrimaryGroup(player);
    }
}
