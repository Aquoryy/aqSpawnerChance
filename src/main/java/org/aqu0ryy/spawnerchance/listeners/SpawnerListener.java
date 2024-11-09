package org.aqu0ryy.spawnerchance.listeners;

import org.aqu0ryy.spawnerchance.Loader;
import org.aqu0ryy.spawnerchance.utils.ChatUtil;
import org.aqu0ryy.spawnerchance.utils.VaultUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class SpawnerListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getInventory().getItemInMainHand();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && stack.getType().name().contains("SPAWN_EGG")) {
            Block block = event.getClickedBlock();

            if (block != null && block.getType() == Material.SPAWNER) {
                Random random = new Random();

                if (random.nextDouble() <= Loader.getInst().getConfig().getDouble("settings.groups." + VaultUtil.getGroup(player) + ".chance")) {
                    ChatUtil.sendMessage(player, Loader.getInst().getConfig().getString("settings.success-set"));
                } else {
                    ChatUtil.sendMessage(player, Loader.getInst().getConfig().getString("settings.failed-set"));

                    if (stack.getAmount() > 1) {
                        stack.setAmount(stack.getAmount() - 1);
                    } else {
                        player.getInventory().setItemInMainHand(null);
                    }

                    event.setCancelled(true);
                }
            }
        }
    }
}
