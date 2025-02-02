package com.github.ringoame196_s_mcPlugin.managers

import org.bukkit.Bukkit
import org.bukkit.entity.Player

object TeamManager {
    val scoreboard = Bukkit.getScoreboardManager()?.mainScoreboard
    fun acquisitionTeamName(player: Player): String? = scoreboard?.getEntryTeam(player.name)?.name
    fun isInTeam(teamName: String, player: Player): Boolean {
        return acquisitionTeamName(player) == teamName
    }
}
