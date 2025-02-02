package com.github.ringoame196_s_mcPlugin

import de.maxhenkel.voicechat.api.VoicechatApi
import de.maxhenkel.voicechat.api.VoicechatPlugin
import de.maxhenkel.voicechat.api.events.EventRegistration
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class ExampleVoiceChatPlugin : VoicechatPlugin {
    /**
     * @return the unique ID for this voice chat plugin
     */
    override fun getPluginId(): String {
        return "TeamVoiceChat"
    }

    /**
     * Called when the voice chat initializes the plugin.
     *
     * @param api the voice chat API
     */

    override fun initialize(api: VoicechatApi?) {
    }

    /**
     * Called once by the voice chat to register all events.
     *
     * @param registration the event registration
     */

    override fun registerEvents(registration: EventRegistration?) {
        registration?.registerEvent(MicrophonePacketEvent::class.java, this::onMicrophonePacket)
    }

    private fun onMicrophonePacket(e: MicrophonePacketEvent) {
        e.senderConnection ?: return
        val api = e.voicechat ?: return
        val player = e.senderConnection?.player?.player as? Player ?: return
        if (!TeamVCManager.isContainsTeamVC(player)) return
        val teamName = TeamManager.acquisitionTeamName(player) ?: return
        val soundPacket = e.packet.toStaticSoundPacket()

        if (Data.isExternalMute) {
            // configで 外部ミュートをtrueにしていた場合 キャンセルする
            e.cancel()
        }

        var c = 0 // 聞こえている人数
        val listenMessage = "${ChatColor.GOLD}[チームVC] [$teamName]${player.name}が発言中..."
        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            if (onlinePlayer == player) continue
            if (!TeamManager.isInTeam(teamName, onlinePlayer)) continue
            val onlineConnection = api.getConnectionOf(onlinePlayer.uniqueId) ?: continue
            api.sendStaticSoundPacketTo(onlineConnection, soundPacket) // 声を流す
            onlinePlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(listenMessage))
            c ++
        }

        val speakerMessage = "${ChatColor.YELLOW}[チームVC] ${teamName}チームの${c}人が聞いています"
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(speakerMessage))
    }
}
