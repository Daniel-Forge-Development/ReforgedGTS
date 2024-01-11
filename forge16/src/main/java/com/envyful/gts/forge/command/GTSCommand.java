package com.envyful.gts.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.SubCommands;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.gts.forge.EnvyGTSForge;
import com.envyful.gts.forge.ui.ViewTradesUI;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;

@Command(
        value = {
                "gts",
                "globaltrade"
        }
)
@Permissible("com.envyful.gts.command.gts")
@SubCommands({
        SellCommand.class, ReloadCommand.class, BroadcastsCommand.class
})
public class GTSCommand {

    @CommandProcessor
    public void onCommand(@Sender ServerPlayerEntity player, String[] args) {
        if (player.isPassenger()) {
            player.sendMessage(UtilChatColour.colour(EnvyGTSForge.getLocale().getMessages().getCannotRideAndGts()), Util.NIL_UUID);
            return;
        }

        if (EnvyGTSForge.getConfig().isEnableOpeningUIMessage()) {
            player.sendMessage(UtilChatColour.colour(EnvyGTSForge.getLocale().getMessages().getOpeningUi()), Util.NIL_UUID);
        }

        StorageProxy.getParty(player).retrieveAll("GTS");
        ViewTradesUI.openUI(EnvyGTSForge.getPlayerManager().getPlayer(player));
    }
}
