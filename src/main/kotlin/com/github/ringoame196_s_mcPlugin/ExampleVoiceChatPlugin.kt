package com.github.ringoame196_s_mcPlugin

import de.maxhenkel.voicechat.api.VoicechatApi
import de.maxhenkel.voicechat.api.VoicechatPlugin
import de.maxhenkel.voicechat.api.events.EventRegistration
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent

class ExampleVoiceChatPlugin : VoicechatPlugin {
	/**
	 * @return the unique ID for this voice chat plugin
	 */
	override fun getPluginId(): String {
		return "SimpleVCTransceiver"
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
		// registration?.registerEvent(MicrophonePacketEvent::class.java, this::onMicrophonePacket)
	}

	private fun onMicrophonePacket(e: MicrophonePacketEvent) {}
}