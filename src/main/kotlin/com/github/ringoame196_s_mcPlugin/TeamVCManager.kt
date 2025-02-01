package com.github.ringoame196_s_mcPlugin

import org.bukkit.entity.Player

object TeamVCManager {
    fun isContainsTeamVC(player: Player): Boolean = Data.containsTeamVCPlayerList.contains(player)

    fun addContainsTeamVC(player: Player) {
        Data.containsTeamVCPlayerList.add(player)
    }

    fun removeContainsTeamVC(player: Player) {
        Data.containsTeamVCPlayerList.remove(player)
    }
}
