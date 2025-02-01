package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.CommandConst
import de.maxhenkel.voicechat.api.BukkitVoicechatService
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private val plugin = this
    override fun onEnable() {
        super.onEnable()
        getCommand(CommandConst.COMMAND_NAME)?.setExecutor(Command())

        val service: BukkitVoicechatService? = server.servicesManager.load(BukkitVoicechatService::class.java)

        if (service != null) {
            val voicechatPlugin = ExampleVoiceChatPlugin()
            service.registerPlugin(voicechatPlugin)
            logger.info("Successfully registered example plugin")
        } else {
            logger.info("Failed to register example plugin")
        }
    }
}
