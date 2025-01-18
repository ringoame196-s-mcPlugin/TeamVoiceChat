package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.TeamVCCommand
import com.github.ringoame196_s_mcPlugin.commands.TeamVCManagerCommand
import com.github.ringoame196_s_mcPlugin.events.Events
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private val plugin = this
    override fun onEnable() {
        super.onEnable()
        server.pluginManager.registerEvents(Events(), plugin)
        getCommand("teamvc")?.setExecutor(TeamVCCommand())
        getCommand("teamvcmanager")?.setExecutor(TeamVCManagerCommand())
    }
}
