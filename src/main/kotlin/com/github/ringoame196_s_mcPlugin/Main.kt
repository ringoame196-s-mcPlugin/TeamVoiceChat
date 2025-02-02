package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.CommandConst
import com.github.ringoame196_s_mcPlugin.managers.TeamVCManager
import de.maxhenkel.voicechat.api.BukkitVoicechatService
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private val voicechatPlugin = ExampleVoiceChatPlugin()
    private val plugin = this

    override fun onEnable() {
        super.onEnable()
        saveDefaultConfig() // configファイル生成
        TeamVCManager.setOnlyTeamVoice(plugin)

        getCommand(CommandConst.COMMAND_NAME)?.setExecutor(Command(plugin))

        val service: BukkitVoicechatService? = server.servicesManager.load(BukkitVoicechatService::class.java)

        // voiceChatPluginを登録
        if (service != null) {
            service.registerPlugin(voicechatPlugin)
            logger.info("Successfully registered example plugin")
        } else {
            logger.info("Failed to register example plugin")
        }
    }

    override fun onDisable() {
        server.servicesManager.unregister(voicechatPlugin)
        logger.info("Successfully unregistered voice chat broadcast plugin")
        super.onDisable()
    }
}
