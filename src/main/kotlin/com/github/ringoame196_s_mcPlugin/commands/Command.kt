package com.github.ringoame196_s_mcPlugin.commands

import com.github.ringoame196_s_mcPlugin.managers.TeamVCManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class Command(private val plugin: Plugin) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        // サブコマンドを入力していない または ADMINパーミッションを持っていなければ 切り替え処理を実行
        if (args.isEmpty() || !sender.hasPermission(CommandConst.ADMIN_PERMISSION)) {
            if (sender is Player) {
                changeTeamVC(sender)
            } else {
                val message = "${ChatColor.RED}このコマンドは、プレイヤーのみ実行可能です"
                sender.sendMessage(message)
            }
            return true
        }

        val subCommand = args[0]
        var target = mutableListOf<Player>()

        if (args.size == 2) {
            target = Bukkit.selectEntities(sender, args[1]).filterIsInstance<Player>().toMutableList()
        }

        when (subCommand) {
            CommandConst.JOIN_COMMAND -> joinTeamVC(sender, target)
            CommandConst.LEAVE_COMMAND -> leaveTeamVC(sender, target)
            CommandConst.RELOAD_COMMAND -> reload(sender)
            else -> {
                val message = "${ChatColor.RED}コマンドの構文が間違っています"
                sender.sendMessage(message)
            }
        }
        return true
    }

    private fun changeTeamVC(player: Player) {
        if (!TeamVCManager.isContainsTeamVC(player)) {
            joinTeamVC(player)
        } else {
            leaveTeamVC(player)
        }
    }

    private fun reload(sender: CommandSender) {
        TeamVCManager.setOnlyTeamVoice(plugin)
        val message = "${ChatColor.GOLD}configをリロードしました"
        sender.sendMessage(message)
    }

    private fun isTargetConfigured(sender: CommandSender, target: List<Player>): Boolean {
        return if (target.isEmpty()) {
            val message = "${ChatColor.RED}ターゲットが設定されていません"
            sender.sendMessage(message)
            false
        } else {
            true
        }
    }

    private fun joinTeamVC(player: Player) {
        if (TeamVCManager.isContainsTeamVC(player)) return
        val message = "${ChatColor.YELLOW}TeamVCがONになりました"
        player.sendMessage(message)
        TeamVCManager.addContainsTeamVC(player)
    }

    private fun joinTeamVC(sender: CommandSender, players: List<Player>) {
        if (!isTargetConfigured(sender, players)) return
        for (player in players) {
            val message = "${ChatColor.GOLD}${player.name}のTeamVCをONにしました"
            sender.sendMessage(message)
            joinTeamVC(player)
        }
    }

    private fun leaveTeamVC(player: Player) {
        if (!TeamVCManager.isContainsTeamVC(player)) return
        val message = "${ChatColor.RED}TeamVCがOFFになりました"
        player.sendMessage(message)
        TeamVCManager.removeContainsTeamVC(player)
    }

    private fun leaveTeamVC(sender: CommandSender, players: List<Player>) {
        if (!isTargetConfigured(sender, players)) return
        for (player in players) {
            val message = "${ChatColor.GOLD}${player.name}のTeamVCをOFFにしました"
            sender.sendMessage(message)
            leaveTeamVC(player)
        }
    }

    override fun onTabComplete(commandSender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        return if (commandSender.hasPermission(CommandConst.ADMIN_PERMISSION)) adminTabComplete(args.size)
        else mutableListOf()
    }

    private fun adminTabComplete(size: Int): MutableList<String> {
        return when (size) {
            1 -> mutableListOf(CommandConst.JOIN_COMMAND, CommandConst.LEAVE_COMMAND)
            2 -> (Bukkit.getOnlinePlayers().map { it.name } + "@a" + "@p" + "@r" + "@s").toMutableList()
            else -> mutableListOf()
        }
    }
}
