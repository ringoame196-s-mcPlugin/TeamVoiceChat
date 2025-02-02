package com.github.ringoame196_s_mcPlugin

import org.bukkit.entity.Player

object Data {
    val containsTeamVCPlayerList = mutableListOf<Player>() // チームVC使用中のプレイヤーリスト
    var isExternalMute: Boolean = true
}
