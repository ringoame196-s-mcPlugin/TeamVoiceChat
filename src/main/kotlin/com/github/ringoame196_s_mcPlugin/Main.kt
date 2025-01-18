package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.TeamVCCommand
import org.bukkit.plugin.java.JavaPlugin
import com.github.ringoame196_s_mcPlugin.events.Events

class Main : JavaPlugin() {
    private val plugin = this
    override fun onEnable() {
        super.onEnable()
        server.pluginManager.registerEvents(Events(), plugin)
        val command = getCommand("command")
        command!!.setExecutor(TeamVCCommand())
        command.tabCompleter = TeamVCCommand()
    }
}
