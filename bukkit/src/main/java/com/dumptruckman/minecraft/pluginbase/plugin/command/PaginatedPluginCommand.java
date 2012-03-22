package com.dumptruckman.minecraft.pluginbase.plugin.command;

import com.dumptruckman.minecraft.pluginbase.locale.Messager;
import com.dumptruckman.minecraft.pluginbase.plugin.AbstractBukkitPlugin;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class PaginatedPluginCommand<T, P extends AbstractBukkitPlugin> extends PaginatedCommand<T> {

    /**
     * The reference to the core.
     */
    protected P plugin;
    /**
     * The reference to {@link com.dumptruckman.minecraft.pluginbase.locale.Messager}.
     */
    protected Messager messager;

    public PaginatedPluginCommand(P plugin) {
        super(plugin);
        this.plugin = plugin;
        this.messager = this.plugin.getMessager();
    }

    @Override
    public abstract void runCommand(CommandSender sender, List<String> args);
}