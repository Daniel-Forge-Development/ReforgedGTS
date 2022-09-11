package com.envyful.gts.forge.listener.discord;

import com.envyful.api.concurrency.UtilConcurrency;
import com.envyful.api.discord.DiscordWebHook;
import com.envyful.api.forge.listener.LazyListener;
import com.envyful.gts.api.discord.DiscordEvent;
import com.envyful.gts.api.discord.DiscordEventManager;
import com.envyful.gts.forge.event.TradeRemoveEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

public class DiscordTradeRemoveListener extends LazyListener {

    public DiscordTradeRemoveListener() {
        super();
    }

    @SubscribeEvent
    public void onTradeCreate(TradeRemoveEvent event) {
        DiscordEvent removeHandler = DiscordEventManager.getRemoveHandler();

        if (removeHandler == null || !removeHandler.isEnabled()) {
            return;
        }

        UtilConcurrency.runAsync(() -> {
            try {
                DiscordWebHook webHook = event.getTrade().getWebHook(removeHandler);

                if (webHook != null) {
                    webHook.execute();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
