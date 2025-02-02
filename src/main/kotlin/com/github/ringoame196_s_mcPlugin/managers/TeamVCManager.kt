package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

object TeamVCManager {
    fun isContainsTeamVC(player: Player): Boolean = Data.containsTeamVCPlayerList.contains(player)

    fun addContainsTeamVC(player: Player) {
        Data.containsTeamVCPlayerList.add(player)
    }

    fun removeContainsTeamVC(player: Player) {
        Data.containsTeamVCPlayerList.remove(player)
    }

    fun setOnlyTeamVoice(plugin: Plugin) {
        Data.onlyTeamVoice = plugin.config.getBoolean("only_team_voice")
    }
}
